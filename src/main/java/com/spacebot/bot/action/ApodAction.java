package com.spacebot.bot.action;

import com.spacebot.service.ApodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class ApodAction extends AbstractAction {

    private final ApodService service;

    @Override
    protected String action() {
        return CallbackAction.APOD.value();
    }

    @Override
    protected BotApiMethod<?> doHandle(Update update) {
        String chatId = update.getCallbackQuery()
                .getMessage()
                .getChatId()
                .toString();
        try {
            String text = service.getTodayApod();
            return new SendMessage(chatId, text);
        } catch (IllegalStateException e) {
            return new SendMessage(chatId, "❌ " + e.getMessage());
        }
    }

}
