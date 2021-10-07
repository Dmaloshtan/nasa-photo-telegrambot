package ru.home.projects.nasaphototelegrambot.bot;

public enum BotState {

    DEFAULT("default"),
    SET_DATE("set_date");

    private final String botState;

    BotState(String botState) {
        this.botState = botState;
    }

    public String getBotState() {
        return botState;
    }
}
