package com.jerieshandal.bowling.util;

import com.jerieshandal.bowling.pojo.Roll;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestHelper {

    private static final Random RANDOM = new Random();

    public static Map<String, List<Roll>> buildProcessedInput() {
        Map<String, List<Roll>> processedInput = new HashMap<>();
        String playerName = randomAlphabetic(8);
        processedInput.put(playerName, buildRolls());
        return processedInput;
    }

    public static List<Roll> buildRolls() {
        List<Roll> rolls = new ArrayList<>();

        for (int i = 0; i < 19; i++) {
            int pins = RANDOM.nextInt(5) + 1;
            rolls.add(Roll.of(String.valueOf(pins)));
        }

        return rolls;
    }
}
