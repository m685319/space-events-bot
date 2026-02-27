package com.spacebot.service.impl;

import com.spacebot.dto.telegram.TelegramSubscriberDTO;
import com.spacebot.service.ApodSubscriptionService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryApodSubscriptionServiceImpl implements ApodSubscriptionService {

    private final Set<TelegramSubscriberDTO> subscribers = ConcurrentHashMap.newKeySet();

    @Override
    public boolean subscribe(TelegramSubscriberDTO telegramSubscriber) {
        TelegramSubscriberDTO existing = findByChatId(telegramSubscriber.getChatId());
        if (existing == null) {
            telegramSubscriber.setSubscribed(true);
            return subscribers.add(telegramSubscriber);
        }
        updateProfile(existing, telegramSubscriber);
        if (!existing.isSubscribed()) {
            existing.setSubscribed(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean unsubscribe(TelegramSubscriberDTO telegramSubscriber) {
        TelegramSubscriberDTO existing = findByChatId(telegramSubscriber.getChatId());
        if (existing == null) {
            return false;
        }
        updateProfile(existing, telegramSubscriber);
        if (!existing.isSubscribed()) {
            return false;
        }
        existing.setSubscribed(false);
        return true;
    }

    @Override
    public Set<TelegramSubscriberDTO> getAllSubscribers() {
        return Set.copyOf(subscribers);
    }

    private TelegramSubscriberDTO findByChatId(Long chatId) {
        for (TelegramSubscriberDTO subscriber : subscribers) {
            if (Objects.equals(subscriber.getChatId(), chatId)) {
                return subscriber;
            }
        }
        return null;
    }

    private void updateProfile(TelegramSubscriberDTO target, TelegramSubscriberDTO source) {
        if (source.getUserId() != null && !Objects.equals(target.getUserId(), source.getUserId())) {
            target.setUserId(source.getUserId());
        }
        if (source.getUsername() != null && !Objects.equals(target.getUsername(), source.getUsername())) {
            target.setUsername(source.getUsername());
        }
        if (source.getFirstName() != null && !Objects.equals(target.getFirstName(), source.getFirstName())) {
            target.setFirstName(source.getFirstName());
        }
        if (source.getLastName() != null && !Objects.equals(target.getLastName(), source.getLastName())) {
            target.setLastName(source.getLastName());
        }
    }

}
