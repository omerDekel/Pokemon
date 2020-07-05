package com.plainid.assignment;

import com.plainid.assignment.dao.PokemonList;
import com.plainid.assignment.dao.TrainerList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

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
}
