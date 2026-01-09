package com.spacebot.service.subscription;

import java.util.Set;

public interface ApodSubscriptionService {

    boolean subscribe(Long chatId);

    boolean unsubscribe(Long chatId);

    Set<Long> getAllSubscribers();
}
