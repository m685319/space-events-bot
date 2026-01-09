package com.spacebot.service.subscription;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryApodSubscriptionService implements ApodSubscriptionService {

    private final Set<Long> subscribers = ConcurrentHashMap.newKeySet();

    @Override
    public boolean subscribe(Long chatId) {
        return subscribers.add(chatId);
    }

    @Override
    public boolean unsubscribe(Long chatId) {
        return subscribers.remove(chatId);
    }

    @Override
    public Set<Long> getAllSubscribers() {
        return Set.copyOf(subscribers);
    }
}
