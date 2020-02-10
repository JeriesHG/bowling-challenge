package com.jerieshandal.bowling.file;

import com.jerieshandal.bowling.TestApplicationConfiguration;
import com.jerieshandal.bowling.pojo.Roll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class FileProcessorTest {

    @Autowired
    private FileProcessor fileProcessor;

    @Test
    void successFileProcessed() throws IOException {
        Path path = Paths.get("src", "test", "resources", "sample.txt");
        Map<String, List<Roll>> processedInput = fileProcessor.process(path.toAbsolutePath().toString());
        assertEquals(2, processedInput.keySet().size());
        assertFalse(processedInput.values().isEmpty());
    }

    @Test
    void invalidFileProcessed() {
        Path path = Paths.get("src", "test", "resources", randomAlphabetic(5) + ".txt");
        try {
            fileProcessor.process(path.toAbsolutePath().toString());
        } catch (IOException ex) {
            assertEquals(path.toAbsolutePath().toString(), ex.getMessage());
        }
    }

    @Test
    void emptyFileProcessed() throws IOException {
        Path path = Paths.get("src", "test", "resources", "empty.txt");
        Map<String, List<Roll>> processedInput = fileProcessor.process(path.toAbsolutePath().toString());
        assertTrue(processedInput.values().isEmpty());
    }

    @Test
    void invalidReverseParametersFileProcessed() {
        Path path = Paths.get("src", "test", "resources", "invalid-1.txt");
        try {
            fileProcessor.process(path.toAbsolutePath().toString());
        } catch (IOException | IllegalArgumentException ex) {
            assertEquals("There's an invalid entry in the file!", ex.getMessage());
        }
    }

    @Test
    void invalidNegativeParametersFileProcessed() {
        Path path = Paths.get("src", "test", "resources", "invalid-2.txt");
        try {
            fileProcessor.process(path.toAbsolutePath().toString());
        } catch (IOException | IllegalArgumentException ex) {
            assertEquals("There's an invalid entry in the file!", ex.getMessage());
        }
    }

    @Test
    void invalidGreaterThan10ScoreFileProcessed() {
        Path path = Paths.get("src", "test", "resources", "invalid-3.txt");
        try {
            fileProcessor.process(path.toAbsolutePath().toString());
        } catch (IOException | IllegalArgumentException ex) {
            assertEquals("There's an invalid entry in the file!", ex.getMessage());
        }
    }

    @Test
    void invalidLetterAsScoreFileProcessed() {
        Path path = Paths.get("src", "test", "resources", "invalid-4.txt");
        try {
            fileProcessor.process(path.toAbsolutePath().toString());
        } catch (IOException | IllegalArgumentException ex) {
            assertEquals("There's an invalid entry in the file!", ex.getMessage());
        }
    }

    @Test
    void invalidSymbolAsScoreFileProcessed() {
        Path path = Paths.get("src", "test", "resources", "invalid-5.txt");
        try {
            fileProcessor.process(path.toAbsolutePath().toString());
        } catch (IOException | IllegalArgumentException ex) {
            assertEquals("There's an invalid entry in the file!", ex.getMessage());
        }
    }

    @Test
    void threeColumnsFileProcessed() {
        Path path = Paths.get("src", "test", "resources", "invalid-6.txt");
        try {
            fileProcessor.process(path.toAbsolutePath().toString());
        } catch (IOException | IllegalArgumentException ex) {
            assertEquals("There's an invalid entry in the file!", ex.getMessage());
        }
    }
}
