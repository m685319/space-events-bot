package com.spacebot.bot.action;

import com.spacebot.dto.telegram.CooldownResultDTO;
import com.spacebot.service.CommandCooldownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class AbstractAction implements BotAction {

    @Autowired
    private CommandCooldownService cooldownService;

    protected abstract String action();

    protected abstract BotApiMethod<?> doHandle(Update update);

    @Override
    public boolean supports(Update update) {
        return update.hasCallbackQuery()
                && action().equals(update.getCallbackQuery().getData());
    }

    @Override
    public BotApiMethod<?> handle(Update update) {
        String chatId = update.getCallbackQuery()
                .getMessage()
                .getChatId()
                .toString();
        CooldownResultDTO result = cooldownService.check(chatId);
        if (!result.isAllowed()) {
            return new SendMessage(
                    chatId,
                    "⏳ Please wait " + result.getRemainingSeconds()
                            + " seconds before requesting again."
            );
        }
        return doHandle(update);
    }



}
