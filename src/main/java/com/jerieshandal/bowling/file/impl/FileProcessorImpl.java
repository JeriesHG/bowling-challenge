package com.jerieshandal.bowling.file.impl;

import com.jerieshandal.bowling.file.FileProcessor;
import com.jerieshandal.bowling.pojo.Roll;
import com.jerieshandal.bowling.util.Messages;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class FileProcessorImpl implements FileProcessor {

    @Autowired
    private Messages messages;

    @Override
    public Map<String, List<Roll>> process(File path) throws IOException {
        Map<String, List<Roll>> processedInput = new HashMap<>();

        List<String> fileLines = Files.readAllLines(path.toPath());
        for (String line : fileLines) {
            String[] splitRows = line.split("\t");
            if (!isValidInput(splitRows)) throw new IllegalArgumentException(messages.getMessage("error.files.entry"));

            String playerName = splitRows[0];
            List<Roll> playerRolls = processedInput.get(playerName);
            if (playerRolls == null) playerRolls = new ArrayList<>();
            playerRolls.add(Roll.of(splitRows[1]));

            processedInput.put(playerName, playerRolls);
        }

        return processedInput;
    }

    private boolean isValidInput(String[] row) {
        if (row.length != 2) return false;

        String firstRow = row[1];
        if (NumberUtils.isDigits(firstRow)) {
            int value = NumberUtils.toInt(firstRow);
            return value >= 0 && value <= 10;
        }

        return Stream.of("X", "F", "/").anyMatch(e -> e.equalsIgnoreCase(firstRow));
    }
}
