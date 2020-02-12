package com.jerieshandal.bowling.file.impl;

import com.jerieshandal.bowling.file.CLIParser;
import com.jerieshandal.bowling.util.CLIOption;
import com.jerieshandal.bowling.util.Messages;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.jerieshandal.bowling.util.CLIOption.FILE;
import static com.jerieshandal.bowling.util.CLIOption.HELP;

@Component
public class CLIParserImpl implements CLIParser {

    @Autowired
    private Messages messages;

    @Override
    public String parse(String... args) throws IOException, ParseException {
        CommandLineParser parser = new DefaultParser();
        Options cliOptions = buildOptions();
        CommandLine line = parser.parse(cliOptions, args);
        if (hasOption(line, HELP)) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("ant", cliOptions);
            return "";
        }

        if (!hasOption(line, FILE)) {
            throw new IllegalArgumentException(messages.getMessage("error.files.path"));
        }

        return getOption(line, FILE);
    }

    private String getOption(CommandLine line, CLIOption cliOption) {
        String optValue = line.getOptionValue(cliOption.getOpt());
        if (optValue == null) optValue = line.getOptionValue(cliOption.getLongOpt());
        return optValue;
    }

    private boolean hasOption(CommandLine line, CLIOption cliOption) {
        return line.hasOption(cliOption.getOpt()) || line.hasOption(cliOption.getLongOpt());
    }

    private Options buildOptions() {
        Options options = new Options();

        options.addOption(HELP.getOpt(), HELP.getLongOpt(), false, HELP.getDescription());
        options.addOption(FILE.getOpt(), FILE.getLongOpt(), true, FILE.getDescription());

        return options;
    }
}
