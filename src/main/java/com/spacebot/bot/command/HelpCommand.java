package com.spacebot.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class HelpCommand extends AbstractCommand {

    @Override
    protected String command() {
        return "help";
    }

    @Override
    protected SendMessage doHandle(Update update) {
        String text = """
                🛰 Space Events Bot commands:

                /start – welcome message
                /launches – upcoming rocket launches
                /help – show this help

                More space features coming soon 🚀
                """;
        return new SendMessage(update.getMessage().getChatId().toString(), text);
    }

}
