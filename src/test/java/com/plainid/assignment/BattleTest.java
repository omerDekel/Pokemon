package com.plainid.assignment;

import com.plainid.assignment.dao.Battle;
import com.plainid.assignment.dao.BattleStatusType;
import com.plainid.assignment.dao.Trainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Omer Dekel on 06/07/2020.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class BattleTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetBattleWin() {
        Battle battle = restTemplate.getForEntity("http://localhost:" + port + "battle/Ryan/OMER",
                Battle.class).getBody();
        assertThat(battle).isNotNull();
        assertThat(battle.getStatus()).isNotNull();
        assertThat(battle.getMessage()).isNotNull();
        assertThat(battle.getStatus()).isEqualTo(BattleStatusType.Success);
        assertThat(battle.getMessage()).isEqualTo("OMER wins");

    }
    @Test
    public void testGetBattleWin2() {
        Battle battle = restTemplate.getForEntity("http://localhost:" + port + "battle/OMER/Ryan",
                Battle.class).getBody();
        assertThat(battle).isNotNull();
        assertThat(battle.getStatus()).isNotNull();
        assertThat(battle.getMessage()).isNotNull();
        assertThat(battle.getStatus()).isEqualTo(BattleStatusType.Success);
        assertThat(battle.getMessage()).isEqualTo("OMER wins");
        //checking that level was updated as excepted
        Trainer trainer = restTemplate.getForEntity("http://localhost:" + port + "/trainer/OMER",
                Trainer.class).getBody();
        assertThat(trainer.getLevel()).isEqualTo(2);

    }
    @Test
    public void testGetBattleCancelled() {
        Battle battle = restTemplate.getForEntity("http://localhost:" + port + "battle/MISTY/Ryan",
                Battle.class).getBody();
        assertThat(battle).isNotNull();
        assertThat(battle.getStatus()).isNotNull();
        assertThat(battle.getMessage()).isNotNull();
        assertThat(battle.getStatus()).isEqualTo(BattleStatusType.Error);
        assertThat(battle.getMessage()).isEqualTo("canceled");
    }
    @Test
    public void testGetBattleTie() {
        Battle battle = restTemplate.getForEntity("http://localhost:" + port + "battle/BROOKS/Ryan",
                Battle.class).getBody();
        assertThat(battle).isNotNull();
        assertThat(battle.getStatus()).isNotNull();
        assertThat(battle.getMessage()).isNotNull();
        assertThat(battle.getStatus()).isEqualTo(BattleStatusType.Draw);
        assertThat(battle.getMessage()).isEqualTo("draw");
        //checking that levels were updated
        Trainer trainerBrooks = restTemplate.getForEntity("http://localhost:" + port + "/trainer/BROOKS",
                Trainer.class).getBody();
        assertThat(trainerBrooks.getLevel()).isEqualTo(2);
        Trainer trainerRyan = restTemplate.getForEntity("http://localhost:" + port + "/trainer/Ryan",
                Trainer.class).getBody();
        assertThat(trainerRyan.getLevel()).isEqualTo(3);
    }
}
