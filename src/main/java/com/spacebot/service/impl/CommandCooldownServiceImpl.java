package com.spacebot.service.impl;

import com.spacebot.dto.telegram.CooldownResultDTO;
import com.spacebot.service.CommandCooldownService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CommandCooldownServiceImpl implements CommandCooldownService {

    private final ConcurrentHashMap<String, Instant> lastExecution = new ConcurrentHashMap<>();
    private static final Duration COOLDOWN = Duration.ofSeconds(30);

    @Override
    public CooldownResultDTO check(String chatId) {
        Instant now = Instant.now();
        Instant last = lastExecution.get(chatId);
        if (last == null) {
            lastExecution.put(chatId, now);
            return new CooldownResultDTO(true, 0);
        }
        Duration elapsed = Duration.between(last, now);
        if (elapsed.compareTo(COOLDOWN) >= 0) {
            lastExecution.put(chatId, now);
            return new CooldownResultDTO(true, 0);
        }
        long remaining = COOLDOWN.minus(elapsed).getSeconds();
        return new CooldownResultDTO(false, remaining);
    }

}
