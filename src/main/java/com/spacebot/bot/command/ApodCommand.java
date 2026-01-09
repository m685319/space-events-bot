package com.spacebot.bot.command;

import com.spacebot.dto.apod.ApodResponseDTO;
import com.spacebot.service.ApodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class ApodCommand extends AbstractCommand {

    private final ApodService service;

    @Override
    protected String command() {
        return "apod";
    }

    @Override
    protected BotApiMethod<?> doHandle(Update update) {
        String chatId = update.getMessage()
                .getChatId()
                .toString();
        ApodResponseDTO apod = service.getTodayApod();
        return new SendMessage(chatId, service.formatApodMessage(apod));
    }

}
