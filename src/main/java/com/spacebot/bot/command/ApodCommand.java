package com.spacebot.bot.command;

import com.spacebot.dto.apod.ApodResponseDTO;
import com.spacebot.service.ApodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ApodCommand extends AbstractCommand {

    private final ApodService service;

    private static final DateTimeFormatter USER_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    protected String command() {
        return "apod";
    }

    @Override
    protected BotApiMethod<?> doHandle(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText().trim();

        try {
            ApodResponseDTO apod = extractDate(text)
                    .map(service::getApodByDate)
                    .orElseGet(service::getTodayApod);

            return new SendMessage(chatId, service.formatApodMessage(apod));

        } catch (DateTimeParseException e) {
            return new SendMessage(
                    chatId,
                    "❌ Invalid date format.\nUse: /apod DD.MM.YYYY (e.g. /apod 10.01.2025)"
            );
        } catch (IllegalArgumentException e) {
            return new SendMessage(chatId, "❌ " + e.getMessage());
        }
    }

    private Optional<LocalDate> extractDate(String text) {
        String[] parts = text.split("\\s+");
        if (parts.length < 2) {
            return Optional.empty();
        }
        return Optional.of(LocalDate.parse(parts[1], USER_DATE_FORMAT));
    }

}
