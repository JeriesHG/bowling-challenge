package com.jerieshandal.bowling.game;

import com.jerieshandal.bowling.TestApplicationConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(classes = TestApplicationConfiguration.class)
class BowlingTest {

    @Autowired
    private Bowling bowling;

    @Test
    void perfectBowlingGame() throws IOException {
        Path path = Paths.get("src", "test", "resources", "files", "sample-perfect.txt");
        String printResult = bowling.processAndPrintScore(path.toAbsolutePath().toString());
        String[] score = printResult.substring(printResult.indexOf("Score")).split("\t\t");
        assertEquals(300, Integer.parseInt(score[score.length - 2]));
    }

    @Test
    void twoPlayerBowlingGame() throws IOException {
        Path path = Paths.get("src", "test", "resources", "files", "sample.txt");
        String printResult = bowling.processAndPrintScore(path.toAbsolutePath().toString());
        String[] score = printResult.substring(printResult.indexOf("Score")).split("\t\t");
        assertEquals(151, Integer.parseInt(score[10]));
        assertEquals(167, Integer.parseInt(score[25]));
    }

    @Test
    void zeroBowlingGame() throws IOException {
        Path path = Paths.get("src", "test", "resources", "files", "sample-zero.txt");
        String printResult = bowling.processAndPrintScore(path.toAbsolutePath().toString());
        String[] score = printResult.substring(printResult.indexOf("Score")).split("\t\t");
        assertEquals(0, Integer.parseInt(score[score.length - 2]));
    }

    @Test
    void fouledBowlingGame() throws IOException {
        Path path = Paths.get("src", "test", "resources", "files", "sample-fouls-only.txt");
        String printResult = bowling.processAndPrintScore(path.toAbsolutePath().toString());
        String[] score = printResult.substring(printResult.indexOf("Score")).split("\t\t");
        assertEquals(0, Integer.parseInt(score[score.length - 2]));
    }
}