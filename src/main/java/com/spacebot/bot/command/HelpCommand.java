package com.spacebot.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class HelpCommand extends AbstractCommand {

    @Override
    protected BotCommandType command() {
        return BotCommandType.HELP;
    }

    @Override
    protected SendMessage doHandle(Update update) {
        String chatId = update.getMessage()
                .getChatId()
                .toString();
        String text = """
                🌌 Space Events Bot

                Explore what’s happening in space right now:

                🪐 /apod — Astronomy picture of the day \s
                🪐 /apod DD.MM.YYYY — Astronomy picture of the day for a specific date \s
                🪐 /subscribe_apod — Subscribe daily picture of the day notification \s
                🪐 /unsubscribe_apod — Unsubscribe daily picture of the day notification \s
                🚀 /launches — Upcoming rocket launches \s
                ☄️ /asteroids — Upcoming asteroids \s
                📰 /news — Latest space news and articles \s
                ▶️ /start — Open the main menu \s
                ℹ️ /help — Show the help message

                Clear skies and happy exploring ✨

                More space features coming soon 🚀
                """;
        return new SendMessage(chatId, text);
    }

}
