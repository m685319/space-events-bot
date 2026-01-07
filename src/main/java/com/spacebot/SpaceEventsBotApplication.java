package com.spacebot;

import com.spacebot.config.TelegramBotProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigurationProperties(TelegramBotProperties.class)
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.spacebot",
        "org.telegram.telegrambots"
})
public class SpaceEventsBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpaceEventsBotApplication.class, args);
    }

}
