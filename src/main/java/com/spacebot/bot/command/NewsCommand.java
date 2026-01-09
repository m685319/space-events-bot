package com.spacebot.bot.command;

import com.spacebot.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class NewsCommand extends AbstractCommand {

    private final NewsService service;

    @Override
    protected String command() {
        return "news";
    }

    @Override
    protected BotApiMethod<?> doHandle(Update update) {
        String chatId = update.getMessage()
                .getChatId()
                .toString();
        String text = service.getLatestNews();
        return new SendMessage(chatId, text);
    }

}
