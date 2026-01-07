package com.spacebot.bot;

import com.spacebot.bot.command.BotCommand;
import com.spacebot.config.TelegramBotProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class SpaceTelegramBot extends TelegramLongPollingBot {
    private final TelegramBotProperties properties;
    private final List<BotCommand> commands;

    public SpaceTelegramBot(TelegramBotProperties properties, List<BotCommand> commands) {
        this.properties = properties;
        this.commands = commands;
    }

    @Override
    public String getBotUsername() {
        return properties.getUsername();
    }

    @Override
    public String getBotToken() {
        return properties.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        commands.stream()
                .filter(command -> command.supports(update))
                .findFirst()
                .map(command -> command.handle(update))
                .ifPresent(this::executeSafely);
    }

    private void executeSafely(BotApiMethod<?> method) {
        try {
            execute(method);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}