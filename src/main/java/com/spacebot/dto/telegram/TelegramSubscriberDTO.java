package com.spacebot.dto.telegram;

import lombok.*;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelegramSubscriberDTO {

    @EqualsAndHashCode.Include
    private Long chatId;
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;

}
