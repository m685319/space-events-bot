package com.spacebot.bot.command;

import com.spacebot.bot.ui.KeyboardFactory;
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

        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        message.setText(
                        "Hello " + firstName + "! 👋\n\n" +
                        "Welcome to Space Events Bot \uD83E\uDE90 \n\n" +
                        "Choose an option below:"
        );
        message.setReplyMarkup(KeyboardFactory.mainMenu());

        return message;
    }
}
