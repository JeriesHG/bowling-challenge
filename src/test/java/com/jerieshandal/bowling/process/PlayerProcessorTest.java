package com.jerieshandal.bowling.process;

import com.jerieshandal.bowling.TestApplicationConfiguration;
import com.jerieshandal.bowling.pojo.Player;
import com.jerieshandal.bowling.pojo.Roll;
import com.jerieshandal.bowling.util.Messages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static com.jerieshandal.bowling.util.TestHelper.buildProcessedInput;
import static com.jerieshandal.bowling.util.TestHelper.buildRolls;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = TestApplicationConfiguration.class)
class PlayerProcessorTest {

    @Autowired
    private PlayerProcessor playerProcessor;
    @Autowired
    private Messages messages;

    @Test
    void successPlayerRetrieval() {
        Map<String, List<Roll>> processedInput = buildProcessedInput();
        String playerName = processedInput.keySet().iterator().next();

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

        try {
            playerProcessor.retrievePlayers(processedInput);
        } catch (IllegalArgumentException ex) {
            assertEquals(messages.getMessage("error.invalid.frames"), ex.getMessage());
        }
    }

    @Test
    void tooManyPlayerRollsPlayerRetrieval() {
        Map<String, List<Roll>> processedInput = buildProcessedInput();
        for (Map.Entry<String, List<Roll>> entry : processedInput.entrySet()) {
            entry.getValue().addAll(buildRolls());
        }

        try {
            playerProcessor.retrievePlayers(processedInput);
        } catch (IllegalArgumentException ex) {
            assertEquals(messages.getMessage("error.invalid.frames"), ex.getMessage());
        }
    }

    @Test
    void consecutiveRollsOver10PlayerRetrieval() {
        Map<String, List<Roll>> processedInput = buildProcessedInput();
        for (Map.Entry<String, List<Roll>> entry : processedInput.entrySet()) {
            for (Roll roll : entry.getValue()) {
                roll.setScore(Integer.parseInt(randomNumeric(1, 3)));
            }
        }

        try {
            playerProcessor.retrievePlayers(processedInput);
        } catch (IllegalArgumentException ex) {
            assertEquals(messages.getMessage("error.consecutive.values"), ex.getMessage());
        }
    }
}
