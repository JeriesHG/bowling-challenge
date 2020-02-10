package com.jerieshandal.bowling.process.impl;

import com.jerieshandal.bowling.pojo.Frame;
import com.jerieshandal.bowling.pojo.Player;
import com.jerieshandal.bowling.pojo.Roll;
import com.jerieshandal.bowling.process.PlayerProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.jerieshandal.bowling.util.Constants.MAX_FRAMES;

@Component
public class PlayerProcessorImpl implements PlayerProcessor {

    @Override
    public List<Player> retrievePlayers(Map<String, List<Roll>> processedInput) {
        List<Player> players = new ArrayList<>();

        for (Map.Entry<String, List<Roll>> entry : processedInput.entrySet()) {
            List<Frame> frames = new ArrayList<>();

            List<Roll> rolls = entry.getValue();
            for (int i = 0; i < rolls.size(); i++) {
                if (frames.size() + 1 == MAX_FRAMES) {
                    frames.add(Frame.of(getRoll(rolls, i), getRoll(rolls, ++i), getRoll(rolls, ++i)));
                    break;
                }

                Roll firstRoll = getRoll(rolls, i);
                if (firstRoll.getScore() == 10) {
                    frames.add(Frame.of(firstRoll));
                    continue;
                }

                Roll secondRoll = getRoll(rolls, ++i);
                frames.add(Frame.of(firstRoll, secondRoll));
            }

            players.add(Player.of(entry.getKey(), frames));
        }

        return players;
    }

    private Roll getRoll(List<Roll> rolls, int index) {
        return index < rolls.size() ? rolls.get(index) : Roll.empty();
    }
}
