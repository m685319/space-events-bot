package com.spacebot.bot.command;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotCommand {

    boolean supports(Update update);

    BotApiMethod<?> handle(Update update);
}