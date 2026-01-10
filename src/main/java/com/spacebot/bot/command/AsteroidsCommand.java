package com.spacebot.bot.command;

import com.spacebot.service.AsteroidsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class AsteroidsCommand extends AbstractCommand {

    private final AsteroidsService service;

    @Override
    protected BotCommandType command() {
        return BotCommandType.ASTEROIDS;
    }

    @Override
    protected SendMessage doHandle(Update update) {
        String chatId = update.getMessage()
                .getChatId()
                .toString();
        String text = service.getUpcomingAsteroids();
        return new SendMessage(chatId, text);
    }

}
