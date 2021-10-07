package ru.home.projects.nasaphototelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

public class MarsCommand implements Command {

    private final SendBotMessageService messageService;

    public MarsCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    public final static String MARS_MESSAGE = "Данный раздел находится в разработке \uD83E\uDD13 и появится чуть позже." +
            " При обновлении раздела вы получите уведомление о его запуске\uD83D\uDD14";

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId().toString(), MARS_MESSAGE);
    }
}
