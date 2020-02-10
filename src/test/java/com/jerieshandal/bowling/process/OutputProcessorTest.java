package com.jerieshandal.bowling.process;

import com.jerieshandal.bowling.TestApplicationConfiguration;
import com.jerieshandal.bowling.pojo.Output;
import com.jerieshandal.bowling.pojo.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static com.jerieshandal.bowling.util.Constants.BLANK;
import static com.jerieshandal.bowling.util.TestHelper.buildProcessedInput;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = TestApplicationConfiguration.class)
class OutputProcessorTest {

    @Autowired
    private OutputProcessor outputProcessor;
    @Autowired
    private PlayerProcessor playerProcessor;

    @Test
    void successOutputLines() {
        List<Player> players = playerProcessor.retrievePlayers(buildProcessedInput());
        List<Output> outputs = outputProcessor.produceOutputLines(players);
        assertFalse(outputs.isEmpty());
        assertEquals(10, outputs.size());
    }

    @Test
    void emptyPlayersOutputLines() {
        List<Output> outputs = outputProcessor.produceOutputLines(Collections.emptyList());
        assertTrue(outputs.isEmpty());
    }

    @Test
    void noFramesOutputLines() {
        List<Player> players = playerProcessor.retrievePlayers(buildProcessedInput());
        Player player = players.get(0);
        player.setFrames(Collections.emptyList());
        players.set(0, player);

        List<Output> outputs = outputProcessor.produceOutputLines(players);
        assertTrue(outputs.isEmpty());
    }

    @Test
    void successPrintedResult() {
        List<Player> players = playerProcessor.retrievePlayers(buildProcessedInput());
        List<Output> outputs = outputProcessor.produceOutputLines(players);
        String printResult = outputProcessor.processOutputResult(outputs);

        assertFalse(printResult.isEmpty());
        String[] score = printResult.substring(printResult.indexOf("Score")).split("\t\t");
        assertEquals(outputs.get(outputs.size() - 1).getFrameScore(), Integer.parseInt(score[score.length - 2]));
    }

    @Test
    void emptyOutputPrintedResult() {
        String printResult = outputProcessor.processOutputResult(Collections.emptyList());
        assertEquals(BLANK, printResult);
    }
}
