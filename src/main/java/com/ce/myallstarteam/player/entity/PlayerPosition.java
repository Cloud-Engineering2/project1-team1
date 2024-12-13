package com.ce.myallstarteam.player.entity;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlayerPosition {
    FIRST_BASEMAN("1루수"),
    SECOND_BASEMAN("2루수"),
    THIRD_BASEMAN("3루수"),
    PITCHER("투수"),
    CATCHER("포수"),
    CENTER_FIELDER("중견수"),
    RIGHT_FIELDER("우익수"),
    LEFT_FIELDER("좌익수"),
    SHORT_STOP("유격수");

    private final String position;

    public static void validateValue(String position){
        Arrays.stream(values())
            .filter(p -> p.getPosition().equals(position))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 포지션입니다."));
    }
}
