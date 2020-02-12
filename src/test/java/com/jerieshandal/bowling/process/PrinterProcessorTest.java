package com.jerieshandal.bowling.process;

import com.jerieshandal.bowling.TestApplicationConfiguration;
import com.jerieshandal.bowling.pojo.Output;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static com.jerieshandal.bowling.util.Constants.BLANK;
import static com.jerieshandal.bowling.util.TestHelper.buildOutputs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ActiveProfiles("test")
@SpringBootTest(classes = TestApplicationConfiguration.class)
class PrinterProcessorTest {

    @Autowired
    private PrinterProcessor printerProcessor;

    @Test
    void successPrintedResult() {
        List<Output> outputs = buildOutputs();
        String printResult = printerProcessor.print(outputs);

        assertFalse(printResult.isEmpty());
        String[] score = printResult.substring(printResult.indexOf("Score")).split("\t\t");
        assertEquals(outputs.get(outputs.size() - 1).getFrameScore(), Integer.parseInt(score[score.length - 2]));
    }

    @Test
    void emptyOutputPrintedResult() {
        String printResult = printerProcessor.print(Collections.emptyList());
        assertEquals(BLANK, printResult);
    }
}
