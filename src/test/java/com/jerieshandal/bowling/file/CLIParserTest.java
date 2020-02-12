package com.jerieshandal.bowling.file;

import com.jerieshandal.bowling.TestApplicationConfiguration;
import com.jerieshandal.bowling.util.Messages;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ActiveProfiles("test")
@SpringBootTest(classes = TestApplicationConfiguration.class)
class CLIParserTest {

    @Autowired
    private CLIParser cliParser;
    @Autowired
    private Messages messages;

    @Test
    void validFilePath() throws IOException, ParseException {
        String randomArgument = RandomStringUtils.randomAlphabetic(2);
        String filePath = cliParser.parse("-f" + randomArgument);
        assertEquals(randomArgument, filePath);
    }

    @Test
    void invalidOptions() {
        String randomArgument = "-" + RandomStringUtils.randomAlphabetic(2);
        try {
            cliParser.parse(randomArgument);
        } catch (IOException | ParseException | IllegalArgumentException ex) {
            assertEquals(String.format("Unrecognized option: %s", randomArgument), ex.getMessage());
        }
    }

    @Test
    void invalidFilePath() {
        try {
            cliParser.parse("");
            fail("Must fail!");
        } catch (IOException | ParseException | IllegalArgumentException ex) {
            assertEquals(messages.getMessage("error.files.path"), ex.getMessage());
        }
    }
}
