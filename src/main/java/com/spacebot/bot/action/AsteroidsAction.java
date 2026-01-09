package com.spacebot.bot.action;

import com.spacebot.service.AsteroidsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class AsteroidsAction extends AbstractAction {

    private final AsteroidsService service;

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
        String text = service.getUpcomingAsteroids();
        return new SendMessage(chatId, text);
    }

}
