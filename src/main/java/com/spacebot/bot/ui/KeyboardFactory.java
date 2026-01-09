package com.spacebot.bot.ui;

import com.spacebot.bot.action.CallbackAction;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class KeyboardFactory {

    public static InlineKeyboardMarkup mainMenu() {
        InlineKeyboardButton launches = InlineKeyboardButton.builder()
                .text("🚀 Launches")
                .callbackData(CallbackAction.LAUNCHES.value())
                .build();
        InlineKeyboardButton asteroids = InlineKeyboardButton.builder()
                .text("☄️ Asteroids")
                .callbackData(CallbackAction.ASTEROIDS.value())
                .build();
        InlineKeyboardButton apod = InlineKeyboardButton.builder()
                .text("🪐 Picture of the Day")
                .callbackData(CallbackAction.APOD.value())
                .build();
        InlineKeyboardButton news = InlineKeyboardButton.builder()
                .text("📰 News")
                .callbackData(CallbackAction.NEWS.value())
                .build();
        return InlineKeyboardMarkup.builder()
                .keyboard(List.of(
                        List.of(launches, asteroids, news),
                        List.of(apod)
                ))
                .build();
    }

}
