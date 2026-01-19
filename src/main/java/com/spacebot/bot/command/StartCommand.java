package com.spacebot.bot.command;

import com.spacebot.bot.ui.KeyboardFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class StartCommand extends AbstractCommand {

    @Override
    protected BotCommandType command() {
        return BotCommandType.START;
    }

    @Override
    public BotApiMethod<?> doHandle(Update update) {
        String chatId = update.getMessage()
                .getChatId()
                .toString();
        String firstName = update.getMessage().getFrom().getFirstName();
        String text = """
            Hello %s! 👋

            Welcome to Space Events Bot 🪐

            Choose an option below:
            """.formatted(firstName);
        SendMessage message = new SendMessage(chatId, text);
        message.setReplyMarkup(KeyboardFactory.mainMenu());
        return message;
    }

}
