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

import static com.jerieshandal.bowling.util.TestHelper.buildPlayers;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = TestApplicationConfiguration.class)
class OutputProcessorTest {

    @Autowired
    private OutputProcessor outputProcessor;

    @Test
    void successOutputLines() {
        List<Output> outputs = outputProcessor.produceOutputLines(buildPlayers());
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
        List<Player> players = buildPlayers();
        Player player = players.get(0);
        player.setFrames(Collections.emptyList());
        players.set(0, player);

        List<Output> outputs = outputProcessor.produceOutputLines(players);
        assertTrue(outputs.isEmpty());
    }
}
