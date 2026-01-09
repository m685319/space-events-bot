package com.spacebot.bot.action;

import com.spacebot.service.LaunchesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class LaunchesAction extends AbstractAction {

    private final LaunchesService service;

    @Override
    protected String action() {
        return CallbackAction.LAUNCHES.value();
    }

    @Override
    protected SendMessage doHandle(Update update) {
        String chatId = update.getCallbackQuery()
                .getMessage()
                .getChatId()
                .toString();
        String text = service.getUpcomingLaunches();
        return new SendMessage(chatId, text);
    }

}
