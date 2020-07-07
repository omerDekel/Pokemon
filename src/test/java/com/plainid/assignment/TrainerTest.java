package com.plainid.assignment;

import com.plainid.assignment.dao.PokemonList;
import com.plainid.assignment.dao.Trainer;
import com.plainid.assignment.dao.TrainerList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
/**
 * Created by Omer Dekel on 03/07/2020.
 */
public class TrainerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void testGetAllTrainers(){
        TrainerList trainerList = restTemplate.getForEntity("http://localhost:" + port + "/trainers",
                TrainerList.class).getBody();
        assertThat(trainerList).isNotNull();
        assertThat(trainerList.getTrainerList()).isNotNull();
    }
    @Test
    public void testGetTrainer(){
        Trainer trainer = restTemplate.getForEntity("http://localhost:" + port + "/trainer/SHLOMO",
                Trainer.class).getBody();
        assertThat(trainer).isNotNull();
        assertThat(trainer.getBag()).isNotNull();
    }
    @Test
    public void testCatchPokemonBagNotFull(){
        List<String> bag = restTemplate.getForEntity("http://localhost:" + port + "/trainer/MISTY/catch/Vulpix",
                List.class).getBody();
        assertThat(bag).isNotNull();
        assertThat(bag).contains("Vulpix");

    }
    @Test
    public void testCatchPokemonBagIsFull(){
        List<String> bag = restTemplate.getForEntity("http://localhost:" + port + "/trainer/BROOKS/catch/Vulpix",
                List.class).getBody();
        assertThat(bag).isNotNull();
        assertThat(bag).contains("Vulpix");
        assertThat(bag).doesNotContain("Lapras");

    }

}
