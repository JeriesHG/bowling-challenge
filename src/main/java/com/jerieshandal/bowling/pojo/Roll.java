package com.jerieshandal.bowling.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Roll {

    private String displayValue;
    private int score;

    public static Roll of(String value) {
        return Roll.builder().displayValue("10".equalsIgnoreCase(value) ? "X" : value).score("F".equalsIgnoreCase(value) ? 0 : Integer.parseInt(value)).build();
    }

    public static Roll empty() {
        return Roll.builder().score(0).displayValue(" ").build();
    }
}
