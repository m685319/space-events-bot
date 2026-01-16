package com.spacebot.dto.telegram;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Builder
public class TelegramSubscriberDTO {

    @EqualsAndHashCode.Include
    private Long chatId;
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;

}
