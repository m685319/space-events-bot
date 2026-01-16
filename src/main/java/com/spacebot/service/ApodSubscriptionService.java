package com.spacebot.service;

import com.spacebot.dto.telegram.TelegramSubscriberDTO;

import java.util.Set;

public interface ApodSubscriptionService {

    boolean subscribe(TelegramSubscriberDTO telegramSubscriber);

    boolean unsubscribe(TelegramSubscriberDTO telegramSubscriber);

    Set<TelegramSubscriberDTO> getAllSubscribers();

}
