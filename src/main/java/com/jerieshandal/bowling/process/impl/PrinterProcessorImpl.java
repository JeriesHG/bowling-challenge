package com.jerieshandal.bowling.process.impl;

import com.jerieshandal.bowling.pojo.Output;
import com.jerieshandal.bowling.process.PrinterProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.jerieshandal.bowling.util.Constants.BLANK;

@Component
public class PrinterProcessorImpl implements PrinterProcessor {

    @Override
    public String print(List<Output> outputs) {
        if (outputs.isEmpty()) return BLANK;

        StringBuilder builder = new StringBuilder("Frame\t\t");
        for (int i = 1; i < 11; i++) {
            builder.append(i).append("\t\t");
        }

        builder.append("\n");

        Map<String, List<Output>> finalResults = outputs.stream().collect(Collectors.groupingBy(Output::getPlayerName));
        for (Map.Entry<String, List<Output>> entry : finalResults.entrySet()) {
            builder.append(entry.getKey()).append("\n");
            builder.append("Pinfalls").append("\t");
            for (Output output : entry.getValue()) {
                builder.append(output.getFirstRoll()).append("\t").append(output.getSecondRoll()).append("\t");
                if (StringUtils.isNotBlank(output.getBonusRoll())) builder.append(output.getBonusRoll());
            }
            builder.append("\n");
            builder.append("Score").append("\t\t");
            for (Output output : entry.getValue()) {
                builder.append(output.getFrameScore()).append("\t\t");
            }
            builder.append("\n");
        }

        String printResult = builder.toString();
        System.out.println(printResult);
        return printResult;
    }
}
