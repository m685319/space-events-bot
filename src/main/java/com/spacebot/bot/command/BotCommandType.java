package com.spacebot.bot.command;

public enum BotCommandType {

    START("start"),
    HELP("help"),
    APOD("apod"),
    ASTEROIDS("asteroids"),
    LAUNCHES("launches"),
    NEWS("news"),
    SUBSCRIBE_APOD("subscribe_apod"),
    UNSUBSCRIBE_APOD("unsubscribe_apod");

    private final String value;

    BotCommandType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
