package com.spacebot.bot.action;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class AbstractAction implements BotAction {

    protected abstract String action();

    @Override
    public boolean supports(Update update) {
        return update.hasCallbackQuery()
                && action().equals(update.getCallbackQuery().getData());
    }

    @Override
    public BotApiMethod<?> handle(Update update) {
        return doHandle(update);
    }

    protected abstract BotApiMethod<?> doHandle(Update update);
}