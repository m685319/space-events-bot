package com.spacebot.scheduler;

import com.spacebot.bot.SpaceTelegramBot;
import com.spacebot.service.ApodService;
import com.spacebot.service.subscription.ApodSubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApodNotificationScheduler {

    private final ApodSubscriptionService subscriptionService;
    private final ApodService apodService;
    private final SpaceTelegramBot bot;

    @Scheduled(cron = "*/30 * * * * *")
    public void sendDailyApodNotification() {
        Set<Long> subscribers = subscriptionService.getAllSubscribers();
        if (subscribers.isEmpty()) {
            log.info("No APOD subscribers, skipping notification");
            return;
        }
        String text;
        try {
            text = apodService.getApodNotification();
        } catch (Exception e) {
            log.error("Failed to fetch APOD for notification", e);
            return;
        }
        for (Long chatId : subscribers) {
            try {
                bot.execute(new SendMessage(chatId.toString(), text));
            } catch (Exception e) {
                log.warn("Failed to send APOD notification to chatId={}", chatId, e);
            }
        }
    }

}
