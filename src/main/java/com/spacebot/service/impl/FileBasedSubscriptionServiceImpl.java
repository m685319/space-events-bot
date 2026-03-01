package com.spacebot.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacebot.dto.telegram.TelegramSubscriberDTO;
import com.spacebot.service.ApodSubscriptionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Primary
@RequiredArgsConstructor
@Service
public class FileBasedSubscriptionServiceImpl implements ApodSubscriptionService {

    private final Set<TelegramSubscriberDTO> subscribers = ConcurrentHashMap.newKeySet();
    private final ObjectMapper objectMapper;
    @Value("${app.subscribers.file}")
    private String subscriberFilePath;
    private Path file;

    @PostConstruct
    public void init() {
        this.file = Path.of(subscriberFilePath);
        ensureDirectoryExists();
        loadFromFileOrCreate();
        log.info("Subscription service initialized with {} subscribers", subscribers.size());
    }

    private void ensureDirectoryExists() {
        try {
            Files.createDirectories(file.getParent());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to create subscriber directory: " + file.getParent(), e);
        }
    }

    private void loadFromFileOrCreate() {
        if (!Files.exists(file)) {
            log.info("Subscriber file not found, creating new file");
            persist();
            return;
        }
        try {
            Set<TelegramSubscriberDTO> stored = objectMapper.readValue(file.toFile(), new TypeReference<Set<TelegramSubscriberDTO>>() {
            });
            subscribers.addAll(stored);
        } catch (Exception e) {
            log.error("Failed to read subscriber file, starting with empty set", e);
        }
    }

    @Override
    public boolean subscribe(TelegramSubscriberDTO telegramSubscriber) {
        TelegramSubscriberDTO existing = findByChatId(telegramSubscriber.getChatId());
        if (existing == null) {
            telegramSubscriber.setSubscribed(true);
            boolean added = subscribers.add(telegramSubscriber);
            if (added) {
                persist();
            }
            log.info("Added {} to subscribers, total subscribers: {}", telegramSubscriber, subscribers.size());
            return added;
        }
        boolean profileUpdated = updateProfile(existing, telegramSubscriber);
        if (!existing.isSubscribed()) {
            existing.setSubscribed(true);
            persist();
            log.info("Re-subscribed {}", existing);
            return true;
        }
        if (profileUpdated) {
            persist();
        }
        log.info("Subscriber already active {}", existing);
        return false;
    }

    @Override
    public boolean unsubscribe(TelegramSubscriberDTO telegramSubscriber) {
        TelegramSubscriberDTO existing = findByChatId(telegramSubscriber.getChatId());
        if (existing == null) {
            log.info("Unsubscribe requested for unknown subscriber {}", telegramSubscriber);
            return false;
        }
        boolean profileUpdated = updateProfile(existing, telegramSubscriber);
        if (!existing.isSubscribed()) {
            if (profileUpdated) {
                persist();
            }
            log.info("Subscriber already inactive {}", existing);
            return false;
        }
        existing.setSubscribed(false);
        persist();
        log.info("Marked {} as unsubscribed", existing);
        return true;
    }

    @Override
    public void recordUser(TelegramSubscriberDTO telegramSubscriber) {
        TelegramSubscriberDTO existing = findByChatId(telegramSubscriber.getChatId());
        if (existing == null) {
            telegramSubscriber.setSubscribed(false);
            boolean added = subscribers.add(telegramSubscriber);
            if (added) {
                persist();
            }
            log.info("Recorded new user {}, total users: {}", telegramSubscriber, subscribers.size());
            return;
        }
        boolean profileUpdated = updateProfile(existing, telegramSubscriber);
        if (profileUpdated) {
            persist();
            log.info("Updated user profile {}", existing);
        }
    }

    @Override
    public Set<TelegramSubscriberDTO> getAllSubscribers() {
        return Set.copyOf(subscribers);
    }

    private synchronized void persist() {
        Path tmp = file.resolveSibling(file.getFileName() + ".tmp");
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(tmp.toFile(), subscribers);
            Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            log.error("Failed to persist subscribers to file", e);
        }
    }

    private TelegramSubscriberDTO findByChatId(Long chatId) {
        for (TelegramSubscriberDTO subscriber : subscribers) {
            if (Objects.equals(subscriber.getChatId(), chatId)) {
                return subscriber;
            }
        }
        return null;
    }

    private boolean updateProfile(TelegramSubscriberDTO target, TelegramSubscriberDTO source) {
        boolean changed = false;
        if (source.getUserId() != null && !Objects.equals(target.getUserId(), source.getUserId())) {
            target.setUserId(source.getUserId());
            changed = true;
        }
        if (source.getUsername() != null && !Objects.equals(target.getUsername(), source.getUsername())) {
            target.setUsername(source.getUsername());
            changed = true;
        }
        if (source.getFirstName() != null && !Objects.equals(target.getFirstName(), source.getFirstName())) {
            target.setFirstName(source.getFirstName());
            changed = true;
        }
        if (source.getLastName() != null && !Objects.equals(target.getLastName(), source.getLastName())) {
            target.setLastName(source.getLastName());
            changed = true;
        }
        return changed;
    }

}
