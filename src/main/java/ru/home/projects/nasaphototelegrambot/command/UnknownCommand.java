package ru.home.projects.nasaphototelegrambot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

public class UnknownCommand implements Command{


    private final SendBotMessageService messageService;

    public UnknownCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    public final static String UNKNOWN_MESSAGE = "Не понимаю вас," +
            " напишите /help чтобы узнать какие команды я поддерживаю";

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_MESSAGE);
    }
}
