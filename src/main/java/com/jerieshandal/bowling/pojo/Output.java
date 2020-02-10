package com.jerieshandal.bowling.pojo;

import lombok.Data;

import static com.jerieshandal.bowling.util.Constants.BLANK;

@Data
public class Output {

    private String playerName;
    private String firstRoll = BLANK;
    private String secondRoll = BLANK;
    private String bonusRoll = BLANK;
    private int frameScore;

    public Output(String playerName) {
        this.playerName = playerName;
    }
}
