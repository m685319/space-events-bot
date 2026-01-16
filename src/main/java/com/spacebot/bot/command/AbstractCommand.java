package com.spacebot.bot.command;

import com.spacebot.dto.telegram.CooldownResultDTO;
import com.spacebot.service.CommandCooldownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public abstract class AbstractCommand implements BotCommand {

    @Autowired
    private CommandCooldownService cooldownService;

    protected abstract BotCommandType command();

    protected abstract BotApiMethod<?> doHandle(Update update);

    @Override
    public boolean supports(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return false;
        }
        List<MessageEntity> entities = update.getMessage().getEntities();
        if (entities == null) {
            return false;
        }
        return entities.stream()
                .filter(e -> "bot_command".equals(e.getType()))
                .map(e -> update.getMessage()
                        .getText()
                        .substring(e.getOffset() + 1, e.getOffset() + e.getLength()))
                .anyMatch(cmd -> cmd.equals(command().value()) || cmd.startsWith(command().value() + "@"));
    }

    @Override
    public BotApiMethod<?> handle(Update update) {
        String chatId = update.getMessage()
                .getChatId()
                .toString();
        CooldownResultDTO result = cooldownService.check(chatId);
        if (!result.isAllowed()) {
            return new SendMessage(
                    chatId.toString(),
                    "⏳ Please wait " + result.getRemainingSeconds()
                            + " seconds before requesting again."
            );
        }
        return doHandle(update);
    }

}
