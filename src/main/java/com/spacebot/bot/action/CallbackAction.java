package com.spacebot.bot.action;

public enum CallbackAction {

    LAUNCHES("launches"),
    ASTEROIDS("asteroids"),
    APOD("apod");

    private final String value;

    CallbackAction(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
