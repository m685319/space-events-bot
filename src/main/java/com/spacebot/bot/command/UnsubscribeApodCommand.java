package com.spacebot.bot.command;

import com.spacebot.service.subscription.ApodSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class UnsubscribeApodCommand extends AbstractCommand {

    private final ApodSubscriptionService subscriptionService;

    @Override
    protected String command() {
        return "unsubscribe_apod";
    }

    @Override
    protected BotApiMethod<?> doHandle(Update update) {
        Long chatId = update.getMessage().getChatId();
        boolean unsubscribed = subscriptionService.unsubscribe(chatId);

        String text = unsubscribed
                ? "🔕 You have unsubscribed from the daily Astronomy Picture of the Day."
                : "ℹ️ You are not subscribed to the daily Astronomy Picture of the Day.";

        return new SendMessage(chatId.toString(), text);
    }
}
