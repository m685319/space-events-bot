package com.spacebot.bot.command;

import com.spacebot.service.subscription.ApodSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class SubscribeApodCommand extends AbstractCommand {

    private final ApodSubscriptionService subscriptionService;

    @Override
    protected BotCommandType command() {
        return BotCommandType.SUBSCRIBE_APOD;
    }

    @Override
    protected BotApiMethod<?> doHandle(Update update) {
        Long chatId = update.getMessage().getChatId();
        boolean subscribed = subscriptionService.subscribe(chatId);
        String text = subscribed
                ? "🔔 You are now subscribed to the daily Astronomy Picture of the Day."
                : "ℹ️ You are already subscribed to the daily Astronomy Picture of the Day.";
        return new SendMessage(chatId.toString(), text);
    }

}
