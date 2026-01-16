package com.spacebot.service.impl;

import com.spacebot.dto.telegram.TelegramSubscriberDTO;
import com.spacebot.service.ApodSubscriptionService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryApodSubscriptionServiceImpl implements ApodSubscriptionService {

    private final Set<TelegramSubscriberDTO> subscribers = ConcurrentHashMap.newKeySet();

    @Override
    public boolean subscribe(TelegramSubscriberDTO telegramSubscriber) {
        return subscribers.add(telegramSubscriber);
    }

    @Override
    public boolean unsubscribe(TelegramSubscriberDTO telegramSubscriber) {
        return subscribers.remove(telegramSubscriber);
    }

    @Override
    public Set<TelegramSubscriberDTO> getAllSubscribers() {
        return Set.copyOf(subscribers);
    }

}
