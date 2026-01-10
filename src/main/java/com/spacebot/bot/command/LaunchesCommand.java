package com.spacebot.bot.command;

import com.spacebot.service.LaunchesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class LaunchesCommand extends AbstractCommand {

    private final LaunchesService service;

    @Override
    protected BotCommandType command() {
        return BotCommandType.LAUNCHES;
    }

    @Override
    protected SendMessage doHandle(Update update) {
        String chatId = update.getMessage()
                .getChatId()
                .toString();
        String text = service.getUpcomingLaunches();
        return new SendMessage(chatId, text);
    }

}
