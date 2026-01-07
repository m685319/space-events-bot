package com.spacebot.spaceeventsbot;

import com.spacebot.config.TelegramBotProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(TelegramBotProperties.class)
@SpringBootApplication
public class SpaceEventsBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpaceEventsBotApplication.class, args);
    }

}
