package com.plainid.assignment.controller;

import com.plainid.assignment.converter.mapper.NameRowMapper;
import com.plainid.assignment.converter.mapper.PokemonRawMapper;
import com.plainid.assignment.converter.mapper.TrainerRowMapper;
import com.plainid.assignment.dao.Pokemon;
import com.plainid.assignment.dao.Trainer;
import com.plainid.assignment.dao.TrainerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Omer Dekel on 03/07/2020.
 * trainer and trainers rest api controller .
 */
@RestController
public class TrainerController {

    private
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Get all trainers in the world
     * @return List of trainers in the world, sorted by level.
     */
    @GetMapping("/trainers")
    public TrainerList getTrainers() {
        List<Trainer> rows = jdbcTemplate.query("SELECT * from TRAINER order by level",new TrainerRowMapper());
        TrainerList trainerList = new TrainerList();
        for (Trainer trainer:rows) {
            List<String> pokemons = getTrainerBag(trainer.getId());
            trainer.setBag(pokemons);
        }
        trainerList.setTrainerList(rows);
        return trainerList;
    }

    /***
     * getTrainer
     * @param name of the trainer.
     * @return trainer data .
     */
    @GetMapping("/trainer/{trainer_name}")
    public Trainer getTrainer(@PathVariable("trainer_name") String name){
        return getTrainerByName(name);
    }

    /***
     * getTrainerByName
     * @param name of the trainer.
     * @return trainer data .
     */
    private Trainer getTrainerByName(String name){
        Trainer t = jdbcTemplate.queryForObject("SELECT * from TRAINER where name = '"+name+"'",new TrainerRowMapper());
        List<String> pokemons = getTrainerBag(t.getId());
        t.setBag(pokemons);
        return t;
    }

    /***
     * catchPokemon.
     * catch pokemon by a trainer.
     * @param trainer_name
     * @param pokemon_name
     * @return the bag after the catch .
     */
    @GetMapping("/trainer/{trainer_name}/catch/{pokemon_name}")
    public List<String> catchPokemon(@PathVariable("trainer_name")String trainer_name,
                                     @PathVariable("pokemon_name")String pokemon_name){
        Trainer trainer = getTrainerByName(trainer_name);
        int trainerId = trainer.getId();
        Timestamp cur_time = new Timestamp(System.currentTimeMillis());
        Pokemon pokemon = jdbcTemplate.queryForObject("select * from pokemon where name ='"+pokemon_name+"'",
                new PokemonRawMapper());
        // if the pokemon isn't in the bag already .
        if(!trainer.bagContains(pokemon.getName())){
            if(trainer.isFull()){
                // replacing the most former pokemon with the new pokemon.
                jdbcTemplate.update(" update pokemons_trainer set pokemons_trainer.time_added ='"+cur_time+
                        "' ,pokemons_trainer.pokemon_id="+pokemon.getId()+" where pokemons_trainer.time_added = (select " +
                        "min(time_added) from pokemons_trainer where pokemons_trainer.trainer_id ='"+ trainerId+ "')") ;
            }else{
                // if the bag isn't full we just add the pokemon to the bag
                jdbcTemplate.update("INSERT INTO POKEMONS_TRAINER (TRAINER_ID, POKEMON_ID) VALUES ("+trainerId+","
                        +pokemon.getId()+");");
            }
            return getTrainerBag(trainerId);
        }
        return trainer.getBag();
    }
    private Pokemon getPokemonByName(String name){
        return jdbcTemplate.queryForObject("select * from pokemon where name ='"+name+"'",
                new PokemonRawMapper());
    }

    /***
     * getTrainerBag
     * return the bag of the pokemon sorted by the time the pokemon was added to the bag .
     * @param trainerID
     * @return
     */
    private List<String> getTrainerBag(int trainerID){
        return jdbcTemplate.query("select pokemon.name from pokemon inner join pokemons_trainer " +
                        "on pokemon.id = pokemons_trainer.pokemon_id where(pokemons_trainer.trainer_id = '" + trainerID +"') " +
                        "order by pokemons_trainer.time_added",
                new NameRowMapper());
    }
}
