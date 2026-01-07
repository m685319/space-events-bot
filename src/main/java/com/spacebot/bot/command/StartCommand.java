package com.spacebot.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class StartCommand implements BotCommand {

    @Override
    public boolean supports(Update update) {
        return update.hasMessage()
                && update.getMessage().hasText()
                && update.getMessage().getText().startsWith("/start");
    }

    @Override
    public BotApiMethod<?> handle(Update update) {
        return new SendMessage(
                update.getMessage().getChatId().toString(),
                "🚀 Welcome to Space Events Bot!\n\n" +
                        "I’ll share rocket launches, asteroids and space photos."
        );
    }
}
