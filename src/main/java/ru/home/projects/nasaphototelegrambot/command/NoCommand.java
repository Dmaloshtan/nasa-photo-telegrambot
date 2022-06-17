package ru.home.projects.nasaphototelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

public class NoCommand implements Command{

    private final SendBotMessageService messageService;

    public NoCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    public final static String NO_MESSAGE = "Я не поддерживаю данную команду." +
            "Чтобы посмотреть список команд щёлкни на панель клавиатуры";

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId().toString(), NO_MESSAGE);
    }
}
