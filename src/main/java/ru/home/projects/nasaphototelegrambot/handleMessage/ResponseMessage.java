package ru.home.projects.nasaphototelegrambot.handleMessage;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ResponseMessage {

    void execute(Update update);

}
