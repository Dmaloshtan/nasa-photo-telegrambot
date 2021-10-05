package ru.home.projects.nasaphototelegrambot.handleMessage;

public enum MessageName {

    DATE("date");

    private final String callbackName;

    MessageName(String callbackName) {
        this.callbackName = callbackName;
    }

    public String getCallbackName() {
        return callbackName;
    }
}
