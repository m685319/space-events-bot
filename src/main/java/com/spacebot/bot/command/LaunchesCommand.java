package com.spacebot.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class LaunchesCommand extends AbstractCommand {

    @Override
    protected String command() {
        return "launches";
    }

    @Override
    protected SendMessage doHandle(Update update) {
        String text = """
                🚀 Upcoming rocket launches

                • Falcon 9 — Jan 12
                • Ariane 6 — Jan 18

                (data source coming soon)
                """;

        return new SendMessage(
                update.getMessage().getChatId().toString(),
                text
        );
    }
}