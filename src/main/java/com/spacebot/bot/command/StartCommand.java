package com.spacebot.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class StartCommand extends AbstractCommand {

    @Override
    protected String command() {
        return "start";
    }

    @Override
    public BotApiMethod<?> doHandle(Update update) {
        String firstName = update.getMessage().getFrom().getFirstName();

        String text = "🚀 Welcome to Space Events Bot, " + firstName + "!\n\n" +
                "I’ll share rocket launches, asteroids and space photos.";

        return new SendMessage(
                update.getMessage().getChatId().toString(),
                text
        );
    }
}
