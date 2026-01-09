package com.spacebot.bot.action;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class AsteroidsAction extends AbstractAction {

    @Override
    protected String action() {
        return CallbackAction.ASTEROIDS.value();
    }

    @Override
    protected BotApiMethod<?> doHandle(Update update) {
        String chatId = update.getCallbackQuery()
                .getMessage()
                .getChatId()
                .toString();
        String text = """
                ☄️ Asteroids

                We’re currently exploring reliable data sources for asteroid tracking.

                This feature is coming soon 🚀
                """;
        return new SendMessage(chatId, text);
    }

}
