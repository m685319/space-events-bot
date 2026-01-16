package com.spacebot.dto.telegram;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CooldownResultDTO {

    private boolean allowed;
    private long remainingSeconds;

}
