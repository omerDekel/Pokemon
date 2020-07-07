package com.plainid.assignment.controller;

import com.plainid.assignment.converter.mapper.NameRowMapper;
import com.plainid.assignment.converter.mapper.PokemonRawMapper;
import com.plainid.assignment.converter.mapper.TrainerRowMapper;
import com.plainid.assignment.dao.Battle;
import com.plainid.assignment.dao.Pokemon;
import com.plainid.assignment.dao.BattleStatusType;
import com.plainid.assignment.dao.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Omer Dekel on 06/07/2020.
 * battle rest api controller  .
 */

@RestController
@RequestMapping("/battle")

public class BattleController {
    private
    Battle result;
    @Autowired
    JdbcTemplate jdbcTemplate;

    /***
     * getBattleResults.
     * responsible for generating a battle between two trainers and updating the data base accordingly.
     * @param trainer1_name name of the first trainer .
     * @param trainer2_name name of the second trainer .
     * @return the results of the battle .
     */
    @GetMapping("/{trainer1_name}/{trainer2_name}")
    Battle getBattleResults(@PathVariable("trainer1_name") String trainer1_name, @PathVariable("trainer2_name") String
            trainer2_name ){
        result = new Battle();
        Trainer t1 = getTrainerByName(trainer1_name);
        Trainer t2 = getTrainerByName(trainer2_name);
        //counters of how many times each pokemon in the bag won for each trainer.
        int count1_win =0,count2_win =0;
        List<String> bag1 = t1.getBag();
        List<String> bag2 = t2.getBag();
        if (t1.isFull() && t2.isFull()){
            //for each pokemon in the bags.
            for (int i = 0; i<t1.getMaxSizeBag(); i++){
                int fightRes = onePairFight(bag1.get(i),bag2.get(i));
                // if the second pokemon won
                if (fightRes<0){
                    count2_win++;
                }else{
                    count1_win+=fightRes;
                }
            }
            //tie
            if (count1_win== count2_win){
                jdbcTemplate.update(" update trainer set level = level+1 where trainer.id ="+t1.getId()+";") ;
                jdbcTemplate.update(" update trainer set level = level+1 where trainer.id ="+t2.getId()+";") ;
                result.setStatus(BattleStatusType.Draw);
                result.setMessage("draw");
            }
            if (count1_win>count2_win){
                setWinnerData(t1);
            } else if (count2_win> count1_win){
                setWinnerData(t2);
            }
            // one of the trainer's bag is not full so the battle is cancelled.
        } else {
            result.setStatus(BattleStatusType.Error);
            result.setMessage("canceled");
        }
        return result;
    }

    /***
     * setWinnerData .
     * updating the level of the winner , and the battle results.
     * @param winner trainer who won.
     */
    private void setWinnerData(Trainer winner){
        jdbcTemplate.update(" update trainer set level = level + 2 where id ="+winner.getId()+";") ;
        result.setStatus(BattleStatusType.Success);
        result.setMessage(winner.getName()+" wins");
    }

    /***
     *onePairFight.
     * @param name1 .
     * @param name2 .
     * @return 1 if first pokemon won, -1 if the second won, else 0.
     */
    private int onePairFight(String name1, String name2){
        Pokemon p1 = jdbcTemplate.queryForObject("select * from pokemon where name ='"+name1+"'",new PokemonRawMapper());
        Pokemon p2 = jdbcTemplate.queryForObject("select * from pokemon where name ='"+name2+"'",new PokemonRawMapper());
        return (p1.getType().compare(p2.getType()));
    }

    /***
     * getTrainerByName
     * @param name of the trainer
     * @return trainer object.
     */
    private Trainer getTrainerByName(String name){
        Trainer t = jdbcTemplate.queryForObject("SELECT * from TRAINER where name = '"+name+"'",new TrainerRowMapper());
        List<String> pokemons = getTrainerBag(t.getId());
        t.setBag(pokemons);
        return t;
    }

    /**
     * getTrainerBag
     * @param trainerID
     * @return the pokemons bag of the trainer .
     */
    private List<String> getTrainerBag(int trainerID){
        List<String> rows = jdbcTemplate.query("select pokemon.name from pokemon inner join pokemons_trainer " +
                        "on pokemon.id = pokemons_trainer.pokemon_id where(pokemons_trainer.trainer_id = '" + trainerID +"') ",
                new NameRowMapper());
        return rows;
    }

}
