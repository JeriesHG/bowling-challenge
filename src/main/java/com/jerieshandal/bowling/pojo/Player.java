package com.jerieshandal.bowling.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    private String name;
    private List<Frame> frames;

    public static Player of(String name, List<Frame> frames) {
        return Player.builder().name(name).frames(frames).build();
    }
}
