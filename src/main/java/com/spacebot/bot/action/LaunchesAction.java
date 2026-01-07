package com.spacebot.bot.action;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class LaunchesAction extends AbstractAction {

    @Override
    protected String action() {
        return "launches";
    }

    @Override
    protected SendMessage doHandle(Update update) {
        return new SendMessage(
                update.getCallbackQuery().getMessage().getChatId().toString(),
                "🪐 Launch data is temporarily unavailable.\nWe’re working on a better source."
        );
    }
}