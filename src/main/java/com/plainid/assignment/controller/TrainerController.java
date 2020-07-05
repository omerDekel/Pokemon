package com.plainid.assignment.controller;

import com.plainid.assignment.converter.mapper.NameRowMapper;
import com.plainid.assignment.converter.mapper.TrainerRawMapper;
import com.plainid.assignment.dao.Trainer;
import com.plainid.assignment.dao.TrainerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
        List<Trainer> rows = jdbcTemplate.query("SELECT * from TRAINER",new TrainerRawMapper());
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
    public List<String> catchp(){
        return null;
    }

    private List<String> getTrainerBag(int trainerID){
        List<String> rows = jdbcTemplate.query("select pokemon.name from pokemon inner join pokemons_trainer " +
                "on pokemon.id = pokemons_trainer.pokemon_id where(pokemons_trainer.trainer_id = '" + trainerID +"') " +
                        "order by pokemons_trainer.time",
                new NameRowMapper());
        return rows;
    }
}
