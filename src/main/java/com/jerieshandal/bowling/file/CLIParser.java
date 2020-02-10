package com.jerieshandal.bowling.file;

import org.apache.commons.cli.ParseException;

import java.io.IOException;

public interface CLIParser {

    String parse(String... args) throws IOException, ParseException;

}
