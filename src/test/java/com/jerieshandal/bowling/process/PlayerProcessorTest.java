package com.jerieshandal.bowling.process;

import com.jerieshandal.bowling.TestApplicationConfiguration;
import com.jerieshandal.bowling.pojo.Player;
import com.jerieshandal.bowling.pojo.Roll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static com.jerieshandal.bowling.util.TestHelper.buildRolls;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = TestApplicationConfiguration.class)
class PlayerProcessorTest {

    @Autowired
    private PlayerProcessor playerProcessor;

    @Test
    void successPlayerRetrieval() {
        Map<String, List<Roll>> processedInput = new HashMap<>();
        String playerName = randomAlphabetic(8);
        processedInput.put(playerName, buildRolls());

        List<Player> players = playerProcessor.retrievePlayers(processedInput);
        assertFalse(players.isEmpty());
        assertFalse(players.get(0).getFrames().isEmpty());
        assertEquals(playerName, players.get(0).getName());
        assertEquals(10, players.get(0).getFrames().size());
    }

    @Test
    void emptyProcessedInputPlayerRetrieval() {
        Map<String, List<Roll>> processedInput = new HashMap<>();
        List<Player> players = playerProcessor.retrievePlayers(processedInput);
        assertTrue(players.isEmpty());
    }

    @Test
    void emptyPlayerRollsPlayerRetrieval() {
        Map<String, List<Roll>> processedInput = new HashMap<>();
        String randomPlayer = randomAlphabetic(8);
        processedInput.put(randomPlayer, Collections.emptyList());

        List<Player> players = playerProcessor.retrievePlayers(processedInput);
        assertFalse(players.isEmpty());
        assertEquals(randomPlayer, players.get(0).getName());
        assertTrue(players.get(0).getFrames().isEmpty());
    }
}
