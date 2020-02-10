package com.jerieshandal.bowling.util;

public enum CLIOption {

    HELP("h", "help", "Help command"),
    FILE("f", "file", "File to parse");

    private final String opt;
    private final String longOpt;
    private final String description;

    CLIOption(String opt, String longOpt, String description) {
        this.opt = opt;
        this.longOpt = longOpt;
        this.description = description;
    }

    public String getOpt() {
        return opt;
    }

    public String getLongOpt() {
        return longOpt;
    }

    public String getDescription() {
        return description;
    }
}
