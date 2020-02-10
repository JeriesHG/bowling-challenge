package com.jerieshandal.bowling.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Frame {

    private Roll firstRoll;
    private Roll secondRoll;
    private Roll bonusRoll;
    private boolean isStrike;
    private boolean isSpare;

    public static Frame of(Roll firstRoll) {
        return Frame.of(firstRoll, Roll.empty(), Roll.empty(), true, false);
    }

    public static Frame of(Roll firstRoll, Roll secondRoll) {
        boolean isSlice = firstRoll.getScore() + secondRoll.getScore() == 10;
        return of(firstRoll, secondRoll, Roll.empty(), false, isSlice);
    }

    public static Frame of(Roll firstRoll, Roll secondRoll, Roll bonusRoll) {
        boolean isSlice = firstRoll.getScore() + secondRoll.getScore() == 10;
        return of(firstRoll, secondRoll, bonusRoll, firstRoll.getScore() == 10, isSlice);
    }

    private static Frame of(Roll firstRoll, Roll secondRoll, Roll bonusRoll, boolean isStrike, boolean isSpare) {
        return Frame.builder().firstRoll(firstRoll).secondRoll(secondRoll).bonusRoll(bonusRoll).isStrike(isStrike).isSpare(isSpare).build();
    }

    public int getTotalScore() {
        return firstRoll.getScore() + secondRoll.getScore() + bonusRoll.getScore();
    }
}
