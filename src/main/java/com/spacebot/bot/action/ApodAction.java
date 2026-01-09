package com.spacebot.bot.action;

import com.spacebot.dto.apod.ApodResponseDTO;
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
        ApodResponseDTO apod = service.getTodayApod();

        String chatId = update.getCallbackQuery()
                .getMessage()
                .getChatId()
                .toString();

        String text = """
                🪐 %s
                📅 %s

                %s

                🔗 %s
                
                🔔 Want daily notifications?
                   Use /subscribe_apod to get APOD every day at 8 AM GMT+3.
                """.formatted(
                apod.getTitle(),
                apod.getDate(),
                apod.getExplanation(),
                apod.getUrl()
        );

        return new SendMessage(chatId, text);
    }
}
