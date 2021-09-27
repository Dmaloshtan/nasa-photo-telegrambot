package ru.home.projects.nasaphototelegrambot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

public class NoCommand implements Command{

    private final SendBotMessageService messageService;

    public NoCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    public final static String NO_MESSAGE = "Я поддерживаю команды, начинающиеся со слеша(/)." +
            "Чтобы посмотреть список команд введите /help";

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId().toString(), NO_MESSAGE);
    }
}
