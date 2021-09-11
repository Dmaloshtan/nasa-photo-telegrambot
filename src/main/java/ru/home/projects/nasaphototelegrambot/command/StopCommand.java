package ru.home.projects.nasaphototelegrambot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

public class StopCommand implements Command{

    @Autowired
    private final SendBotMessageService messageService;

    public StopCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    public final static String STOP_MESSAGE = "Деактивировал все ваши подписки \\uD83D\\uDE1F.";

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
    }

}
