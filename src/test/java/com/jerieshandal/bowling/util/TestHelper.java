package com.jerieshandal.bowling.util;

import com.jerieshandal.bowling.pojo.Frame;
import com.jerieshandal.bowling.pojo.Output;
import com.jerieshandal.bowling.pojo.Player;
import com.jerieshandal.bowling.pojo.Roll;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

import static com.jerieshandal.bowling.util.RollHelper.getRoll;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestHelper {

    private static final Random RANDOM = new Random();

    public static List<Output> buildOutputs() {
        List<Output> outputs = new ArrayList<>();
        List<Player> players = buildPlayers();

        for (Player player : players) {
            List<Frame> frames = player.getFrames();
            int totalScore = 0;
            for (int i = 0; i < frames.size(); i++) {
                Frame frame = frames.get(i);

                Output output = new Output(player.getName());
                output.setFirstRoll(frame.getFirstRoll().getDisplayValue());
                output.setSecondRoll(frame.getSecondRoll().getDisplayValue());
                output.setBonusRoll(frame.getBonusRoll().getDisplayValue());

                totalScore += frame.getTotalScore();
                if (i + i < frames.size()) {
                    Frame nextFrame = frames.get(i + 1);
                    totalScore += nextFrame.getTotalScore();
                }

                output.setFrameScore(totalScore);
                outputs.add(output);
            }
        }


        return outputs;
    }

    public static List<Player> buildPlayers() {
        List<Player> players = new ArrayList<>();

        players.add(buildRandomPlayer());

        return players;
    }

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

    public static Player buildRandomPlayer() {
        Player player = new Player();
        player.setName(randomAlphabetic(5));

        List<Frame> frames = new ArrayList<>();
        List<Roll> rolls = buildRolls();
        for (Iterator<Roll> iterator = rolls.iterator(); iterator.hasNext(); ) {
            frames.add(Frame.of(getRoll(iterator), getRoll(iterator)));
        }

        player.setFrames(frames);

        return player;
    }
}
