package com.jerieshandal.bowling.process.impl;

import com.jerieshandal.bowling.pojo.Frame;
import com.jerieshandal.bowling.pojo.Player;
import com.jerieshandal.bowling.pojo.Roll;
import com.jerieshandal.bowling.process.PlayerProcessor;
import com.jerieshandal.bowling.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.jerieshandal.bowling.util.Constants.MAX_FRAMES;
import static com.jerieshandal.bowling.util.RollHelper.getRoll;

@Component
public class PlayerProcessorImpl implements PlayerProcessor {

    @Autowired
    private Messages messages;

    @Override
    public List<Player> retrievePlayers(Map<String, List<Roll>> processedInput) {
        List<Player> players = new ArrayList<>();

        for (Map.Entry<String, List<Roll>> entry : processedInput.entrySet()) {
            List<Frame> frames = new ArrayList<>();

            List<Roll> rolls = entry.getValue();
            for (Iterator<Roll> iterator = rolls.iterator(); iterator.hasNext(); ) {
                if (frames.size() + 1 == MAX_FRAMES) {
                    frames.add(Frame.of(getRoll(iterator), getRoll(iterator), getRoll(iterator)));
                    break;
                }

                Roll firstRoll = getRoll(iterator);
                if (firstRoll.getScore() == 10) {
                    frames.add(Frame.of(firstRoll));
                    continue;
                }

                Roll secondRoll = getRoll(iterator);
                validateOpenFrameRolls(firstRoll, secondRoll);
                frames.add(Frame.of(firstRoll, secondRoll));
            }

            if (!rolls.isEmpty() || frames.size() != 10) {
                throw new IllegalArgumentException(messages.getMessage("error.invalid.frames"));
            }

            players.add(Player.of(entry.getKey(), frames));
        }

        return players;
    }

    private void validateOpenFrameRolls(Roll firstRoll, Roll secondRoll) {
        if (firstRoll.getScore() + secondRoll.getScore() > 10) {
            throw new IllegalArgumentException(messages.getMessage("error.consecutive.values"));
        }
    }
}
