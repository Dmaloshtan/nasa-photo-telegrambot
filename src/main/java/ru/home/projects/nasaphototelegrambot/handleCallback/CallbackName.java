package ru.home.projects.nasaphototelegrambot.handleCallback;

public enum CallbackName {

    APODTODAY("today"),
    APODDIF("DatePhoto"),
    SUBSCRIBE("subscribe"),
    UNSUBSCRIBE("unsubscribe"),
    CURIOSITY_ROVER("curiosity"),
    OPPORTUNITY_ROVER("opportunity"),
    SPIRIT_ROVER("spirit");

    private final String callbackName;

    CallbackName(String callbackName) {
        this.callbackName = callbackName;
    }

    public String getCallbackName() {
        return callbackName;
    }
}
