package com.jerieshandal.bowling.process.impl;

import com.jerieshandal.bowling.pojo.Frame;
import com.jerieshandal.bowling.pojo.Output;
import com.jerieshandal.bowling.pojo.Player;
import com.jerieshandal.bowling.process.OutputProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.jerieshandal.bowling.util.Constants.BLANK;

@Component
public class OutputProcessorImpl implements OutputProcessor {

    @Override
    public List<Output> produceOutputLines(List<Player> players) {
        List<Output> outputs = new ArrayList<>();

        for (Player player : players) {

            int totalScore = 0;
            List<Frame> frames = player.getFrames();
            for (int i = 0; i < frames.size(); i++) {
                Frame currentFrame = frames.get(i);
                totalScore += calculateScore(frames, currentFrame, i);
                outputs.add(produceOutput(player, currentFrame, totalScore, i + 1 == 10));
            }
        }

        return outputs;
    }

    @Override
    public String processOutputResult(List<Output> outputs) {
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

        return builder.toString();
    }

    private Output produceOutput(Player currentPlayer, Frame currentFrame, int frameScore, boolean lastFrame) {
        Output output = new Output(currentPlayer.getName());

        if (!lastFrame) {
            if (currentFrame.isSpare()) {
                output.setFirstRoll(String.valueOf(currentFrame.getFirstRoll().getScore()));
                output.setSecondRoll("/");
            } else if (currentFrame.isStrike()) {
                output.setSecondRoll("X");
            } else {
                output.setFirstRoll(currentFrame.getFirstRoll().getDisplayValue());
                output.setSecondRoll(currentFrame.getSecondRoll().getDisplayValue());
            }
        } else {
            output.setFirstRoll(currentFrame.getFirstRoll().getDisplayValue());
            if (currentFrame.isSpare()) {
                output.setSecondRoll("/");
            } else {
                output.setSecondRoll(currentFrame.getSecondRoll().getDisplayValue());
            }
            output.setBonusRoll(currentFrame.getBonusRoll().getDisplayValue());
        }

        output.setFrameScore(frameScore);
        return output;
    }

    private int calculateScore(List<Frame> frames, Frame currentFrame, int index) {
        int calculatedScore;

        if (index + 1 == frames.size()) {
            return currentFrame.getTotalScore();
        }

        if (currentFrame.isSpare()) {
            Frame nextFrame = frames.get(index + 1);
            calculatedScore = currentFrame.getTotalScore() + nextFrame.getFirstRoll().getScore();
        } else if (currentFrame.isStrike()) {
            Frame nextFrame = frames.get(index + 1);
            if (nextFrame.isStrike() && index + 2 < frames.size()) {
                Frame secondFrame = frames.get(index + 2);
                calculatedScore = currentFrame.getTotalScore() + nextFrame.getFirstRoll().getScore() + secondFrame.getFirstRoll().getScore();
            } else {
                calculatedScore = currentFrame.getTotalScore() + nextFrame.getFirstRoll().getScore() + nextFrame.getSecondRoll().getScore();
            }
        } else {
            calculatedScore = currentFrame.getTotalScore();
        }

        return calculatedScore;
    }
}