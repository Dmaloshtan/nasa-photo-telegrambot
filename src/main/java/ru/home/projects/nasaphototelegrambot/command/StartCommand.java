package ru.home.projects.nasaphototelegrambot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

public class StartCommand implements Command{

    @Autowired
    private final SendBotMessageService messageService;

    public final static String START_MESSAGE = "Привет. Я NasaPhoto Telegram Bot. " +
            "Я могу присылать фотографии космоса с сайта Nasa";

    public StartCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(Update update) {
    messageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
