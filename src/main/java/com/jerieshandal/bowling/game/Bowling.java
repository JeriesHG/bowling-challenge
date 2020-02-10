package com.jerieshandal.bowling.game;

import org.apache.commons.cli.ParseException;

import java.io.IOException;

public interface Bowling {

    String produceScore(String filePath) throws IOException, ParseException;
}
