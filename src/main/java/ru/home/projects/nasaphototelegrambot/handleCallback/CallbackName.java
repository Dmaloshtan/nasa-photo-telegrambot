package ru.home.projects.nasaphototelegrambot.handleCallback;

public enum CallbackName {

    APODTODAY("today"),
    SETDATE("set_date"),
    APODDIF("DatePhoto");

    private final String callbackName;

    CallbackName(String callbackName) {
        this.callbackName = callbackName;
    }

    public String getCallbackName() {
        return callbackName;
    }
}
