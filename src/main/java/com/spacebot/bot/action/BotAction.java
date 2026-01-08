package com.spacebot.bot.action;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotAction {

    boolean supports(Update update);

    BotApiMethod<?> handle(Update update);

}
