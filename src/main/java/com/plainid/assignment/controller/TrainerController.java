package com.plainid.assignment.controller;

import com.plainid.assignment.converter.mapper.NameRowMapper;
import com.plainid.assignment.converter.mapper.PokemonRawMapper;
import com.plainid.assignment.converter.mapper.TrainerRawMapper;
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
 */
@RestController
public class TrainerController {

    private

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Get all pokemons in the world
     * @return List of pokemons in the world.
     */
    @GetMapping("/trainers")
    public TrainerList getTrainers() {
        List<Trainer> rows = jdbcTemplate.query("SELECT * from TRAINER order by level",new TrainerRawMapper());
        TrainerList trainerList = new TrainerList();
        for (Trainer trainer:rows) {
            List<String> pokemons = getTrainerBag(trainer.getId());
            trainer.setBag(pokemons);
        }
        trainerList.setTrainerList(rows);
        return trainerList;
    }
    @GetMapping("/trainer/{trainer_name}")
    public Trainer getTrainer(@PathVariable("trainer_name") String name){
        Trainer t = jdbcTemplate.queryForObject("SELECT * from TRAINER where name = '"+name+"'",new TrainerRawMapper());
        List<String> pokemons = getTrainerBag(t.getId());
        t.setBag(pokemons);
        return t;
    }
    @GetMapping("/trainer/{trainer_name}/catch/{pokemon_name}")
    public List<String> catchPokemon(@PathVariable("trainer_name")String trainer_name,
                                     @PathVariable("pokemon_name")String pokemon_name){
        Trainer trainer = getTrainer(trainer_name);
        int trainerId = trainer.getId();
        Timestamp cur_time = new Timestamp(System.currentTimeMillis());
        Pokemon pokemon = jdbcTemplate.queryForObject("select * from pokemon where name ='"+pokemon_name+"'",
                new PokemonRawMapper());
        List<String> bag = getTrainerBag(trainerId);
        if(!bag.contains(pokemon)){
            if(bag.size()==3){
                jdbcTemplate.queryForObject(" update Pokemon set trainer_id ="+trainerId+",time_added ="+cur_time+"" +
                                "where time_added =(select min(time_added) from pokemon)"+pokemon_name+"'",
                        new PokemonRawMapper());
            }
        }
        return null;
    }
    private Pokemon getPokemonByName(String name){
        Pokemon p = jdbcTemplate.queryForObject("select * from pokemon where name ='"+name+"'",
                new PokemonRawMapper());
        return p;
    }
    private List<String> getTrainerBag(int trainerID){
        List<String> rows = jdbcTemplate.query("select pokemon.name from pokemon" +
                        " where(pokemon.trainer_id = '" + trainerID +"') " +
                        "order by pokemon.TIME_ADDED",
                new NameRowMapper());
        return rows;
    }
}
