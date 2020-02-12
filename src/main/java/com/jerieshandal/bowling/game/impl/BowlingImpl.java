package com.jerieshandal.bowling.game.impl;

import com.jerieshandal.bowling.file.FileProcessor;
import com.jerieshandal.bowling.game.Bowling;
import com.jerieshandal.bowling.pojo.Output;
import com.jerieshandal.bowling.pojo.Player;
import com.jerieshandal.bowling.pojo.Roll;
import com.jerieshandal.bowling.process.OutputProcessor;
import com.jerieshandal.bowling.process.PlayerProcessor;
import com.jerieshandal.bowling.process.PrinterProcessor;
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
    @Autowired
    private PrinterProcessor printer;

    @Override
    public String processScore(String filePath) throws IOException {
        Map<String, List<Roll>> processedFile = fileProcessor.process(filePath);
        List<Player> players = playerProcessor.retrievePlayers(processedFile);
        List<Output> outputs = outputProcessor.produceOutputLines(players);
        return printer.print(outputs);
    }
}
