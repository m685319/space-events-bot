package com.spacebot.bot;

import com.spacebot.bot.action.BotAction;
import com.spacebot.bot.command.BotCommand;
import com.spacebot.config.TelegramBotProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Slf4j
@Component
public class SpaceTelegramBot extends TelegramLongPollingBot {

    private final TelegramBotProperties properties;
    private final List<BotCommand> commands;
    private final List<BotAction> actions;

    public SpaceTelegramBot(TelegramBotProperties properties, List<BotCommand> commands, List<BotAction> actions) {
        super(properties.getToken());
        this.properties = properties;
        this.commands = commands;
        this.actions = actions;
    }

    @Override
    public String getBotUsername() {
        return properties.getUsername();
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Update received");
        if (update.hasCallbackQuery()) {
            actions.stream()
                    .filter(a -> a.supports(update))
                    .findFirst()
                    .map(a -> a.handle(update))
                    .ifPresent(this::executeSafely);
            return;
        }
        commands.stream()
                .filter(c -> c.supports(update))
                .findFirst()
                .map(c -> c.handle(update))
                .ifPresent(this::executeSafely);
    }

    private void handleUnknownCommand(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }
        SendMessage message = new SendMessage(
                update.getMessage().getChatId().toString(),
                "🤔 I don’t know this command.\nType /help to see available commands."
        );
        executeSafely(message);
    }

    private void executeSafely(BotApiMethod<?> method) {
        try {
            execute(method);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
