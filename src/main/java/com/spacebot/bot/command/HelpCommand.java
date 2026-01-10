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
        String text = """
                🛰 Space Events Bot
                             
                Explore what’s happening in space right now:
                             
                🚀 /launches — upcoming rocket launches \s
                📰 /news — latest space news and articles \s
                🪐 /apod — astronomy picture of the day \s
                🪐 /apod DD.MM.YYYY — picture of the day for a specific date \s
                ▶️ /start — open the main menu \s
                ℹ️ /help — show this help message
                             
                Clear skies and happy exploring ✨

                More space features coming soon 🚀
                """;
        return new SendMessage(update.getMessage().getChatId().toString(), text);
    }

}
