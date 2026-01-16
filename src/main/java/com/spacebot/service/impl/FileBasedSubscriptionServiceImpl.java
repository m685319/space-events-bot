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
        boolean added = subscribers.add(telegramSubscriber);
        if (added) {
            persist();
        }
        log.info("Added {} to subscribers, total subscribers: {}", telegramSubscriber, subscribers.size());
        return added;
    }

    @Override
    public boolean unsubscribe(TelegramSubscriberDTO telegramSubscriber) {
        boolean removed = subscribers.remove(telegramSubscriber);
        if (removed) {
            persist();
        }
        log.info("Removed {} from subscribers, total subscribers: {}", telegramSubscriber, subscribers.size());
        return removed;
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

}
