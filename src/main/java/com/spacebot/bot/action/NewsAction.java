package com.spacebot.bot.action;

import com.spacebot.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class NewsAction extends AbstractAction {

    private final NewsService service;

    @Override
    protected String action() {
        return CallbackAction.NEWS.value();
    }

    @Override
    protected BotApiMethod<?> doHandle(Update update) {
        String chatId = update.getCallbackQuery()
                .getMessage()
                .getChatId()
                .toString();

        String text = service.getLatestNews();
        return new SendMessage(chatId, text);
    }
}

