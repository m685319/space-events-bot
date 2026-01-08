package com.spacebot.bot.command;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public abstract class AbstractCommand implements BotCommand {

    protected abstract String command();

    protected abstract BotApiMethod<?> doHandle(Update update);

    @Override
    public boolean supports(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return false;
        }
        List<MessageEntity> entities = update.getMessage().getEntities();
        if (entities == null) {
            return false;
        }
        return entities.stream()
                .filter(e -> "bot_command".equals(e.getType()))
                .map(e -> update.getMessage()
                        .getText()
                        .substring(e.getOffset() + 1, e.getOffset() + e.getLength()))
                .anyMatch(cmd -> cmd.equals(command()) || cmd.startsWith(command() + "@"));
    }

    @Override
    public BotApiMethod<?> handle(Update update) {
        return doHandle(update);
    }

}