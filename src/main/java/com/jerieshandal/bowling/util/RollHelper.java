package com.jerieshandal.bowling.util;

import com.jerieshandal.bowling.pojo.Roll;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Iterator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RollHelper {

    public static Roll getRoll(Iterator<Roll> iterator) {
        if (iterator.hasNext()) {
            Roll roll = iterator.next();
            iterator.remove();
            return roll;
        } else return Roll.empty();
    }
}
