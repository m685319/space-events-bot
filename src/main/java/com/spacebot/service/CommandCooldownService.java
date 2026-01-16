package com.spacebot.service;

import com.spacebot.dto.telegram.CooldownResultDTO;

public interface CommandCooldownService {

    CooldownResultDTO check(String chatId);

}
