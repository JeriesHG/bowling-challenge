package com.jerieshandal.bowling.process;

import com.jerieshandal.bowling.pojo.Player;
import com.jerieshandal.bowling.pojo.Roll;

import java.util.List;
import java.util.Map;

public interface PlayerProcessor {

    List<Player> retrievePlayers(Map<String, List<Roll>> processedInput);
}
