package ru.home.projects.nasaphototelegrambot.handleCallback;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ResponseCallbackQuery {

    void execute(Update update);

}
