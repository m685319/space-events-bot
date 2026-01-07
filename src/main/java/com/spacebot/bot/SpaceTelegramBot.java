package com.spacebot.bot;

import com.spacebot.config.TelegramBotProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class SpaceTelegramBot extends TelegramLongPollingBot {

    private final TelegramBotProperties properties;

    public SpaceTelegramBot(TelegramBotProperties properties) {
        super(properties.getToken());
        this.properties = properties;
    }

    @Override
    public String getBotUsername() {
        return properties.getUsername();
    }

    @Override
    public void onUpdateReceived(Update update) {
    }
}