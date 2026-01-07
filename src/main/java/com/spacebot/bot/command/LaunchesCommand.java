package com.spacebot.bot.command;

import com.spacebot.dto.SpacexLaunchDto;
import com.spacebot.service.LaunchesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LaunchesCommand extends AbstractCommand {
    private final LaunchesService service;

    @Override
    protected String command() {
        return "launches";
    }

    @Override
    protected SendMessage doHandle(Update update) {
        List<SpacexLaunchDto> launches = service.getUpcomingLaunches();

        String text = launches.stream()
                .limit(3)
                .map(l -> "• " + l.getName() + " — " + l.getDate_utc())
                .reduce("🚀 Upcoming SpaceX launches\n\n",
                        (a, b) -> a + b + "\n");

        return new SendMessage(
                update.getMessage().getChatId().toString(),
                text
        );
    }
}