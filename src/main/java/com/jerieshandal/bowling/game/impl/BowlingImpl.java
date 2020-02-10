package com.jerieshandal.bowling.game.impl;

import com.jerieshandal.bowling.file.FileProcessor;
import com.jerieshandal.bowling.game.Bowling;
import com.jerieshandal.bowling.pojo.Output;
import com.jerieshandal.bowling.pojo.Player;
import com.jerieshandal.bowling.pojo.Roll;
import com.jerieshandal.bowling.process.PlayerProcessor;
import com.jerieshandal.bowling.process.OutputProcessor;
import org.apache.commons.cli.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class BowlingImpl implements Bowling {

    @Autowired
    private FileProcessor fileProcessor;
    @Autowired
    private PlayerProcessor playerProcessor;
    @Autowired
    private OutputProcessor outputProcessor;

    @Override
    public String produceScore(String filePath) throws IOException, ParseException {
        Map<String, List<Roll>> processedFile = fileProcessor.process(filePath);
        List<Player> players = playerProcessor.retrievePlayers(processedFile);
        List<Output> outputs = outputProcessor.produceOutputLines(players);
        return outputProcessor.printResult(outputs);
    }
}
