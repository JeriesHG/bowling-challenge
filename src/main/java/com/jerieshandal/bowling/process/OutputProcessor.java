package com.jerieshandal.bowling.process;

import com.jerieshandal.bowling.pojo.Output;
import com.jerieshandal.bowling.pojo.Player;

import java.util.List;

public interface OutputProcessor {

    List<Output> produceOutputLines(List<Player> players);

    String printResult(List<Output> outputs);
}
