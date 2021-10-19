package ru.home.projects.nasaphototelegrambot.handleCallback;

public enum CallbackName {

    APODTODAY("today"),
    APODDIF("DatePhoto"),
    SUBSCRIBE("subscribe"),
    UNSUBSCRIBE("unsubscribe");

    private final String callbackName;

    CallbackName(String callbackName) {
        this.callbackName = callbackName;
    }

    public String getCallbackName() {
        return callbackName;
    }
}
