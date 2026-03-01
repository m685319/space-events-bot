package com.spacebot.bot.action;

import com.spacebot.dto.telegram.CooldownResultDTO;
import com.spacebot.dto.telegram.TelegramSubscriberDTO;
import com.spacebot.service.CommandCooldownService;
import com.spacebot.service.ApodSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class AbstractAction implements BotAction {

    @Autowired
    private CommandCooldownService cooldownService;

    @Autowired
    private ApodSubscriptionService subscriptionService;

    protected abstract String action();

    protected abstract BotApiMethod<?> doHandle(Update update);

    @Override
    public boolean supports(Update update) {
        return update.hasCallbackQuery()
                && action().equals(update.getCallbackQuery().getData());
    }

    @Override
    public BotApiMethod<?> handle(Update update) {
        recordCallbackUser(update);
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

    private void recordCallbackUser(Update update) {
        TelegramSubscriberDTO subscriber = TelegramSubscriberDTO.builder()
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .userId(update.getCallbackQuery().getFrom().getId())
                .username(update.getCallbackQuery().getFrom().getUserName())
                .firstName(update.getCallbackQuery().getFrom().getFirstName())
                .lastName(update.getCallbackQuery().getFrom().getLastName())
                .build();
        subscriptionService.recordUser(subscriber);
    }
}
