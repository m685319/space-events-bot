package com.spacebot.bot.ui;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class KeyboardFactory {

    public static InlineKeyboardMarkup mainMenu() {
        InlineKeyboardButton launches = InlineKeyboardButton.builder()
                .text("🚀 Launches")
                .callbackData("launches")
                .build();

        InlineKeyboardButton asteroids = InlineKeyboardButton.builder()
                .text("☄️ Asteroids")
                .callbackData("asteroids")
                .build();

        InlineKeyboardButton apod = InlineKeyboardButton.builder()
                .text("🪐 Picture of the Day")
                .callbackData("apod")
                .build();

        return InlineKeyboardMarkup.builder()
                .keyboard(List.of(
                        List.of(launches, asteroids),
                        List.of(apod)
                ))
                .build();
    }
}