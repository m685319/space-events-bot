package com.spacebot.bot.command;

import com.spacebot.dto.telegram.TelegramSubscriberDTO;
import com.spacebot.service.ApodSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
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
        Message message = update.getMessage();
        TelegramSubscriberDTO telegramSubscriber = TelegramSubscriberDTO.builder()
                .chatId(message.getChatId())
                .userId(message.getFrom().getId())
                .username(message.getFrom().getUserName())
                .firstName(message.getFrom().getFirstName())
                .lastName(message.getFrom().getLastName())
                .build();
        boolean subscribed = subscriptionService.subscribe(telegramSubscriber);
        String chatId = telegramSubscriber.getChatId().toString();
        String text = subscribed
                ? "🔔 You are now subscribed to the daily Astronomy Picture of the Day."
                : "ℹ️ You are already subscribed to the daily Astronomy Picture of the Day.";
        return new SendMessage(chatId, text);
    }

}
