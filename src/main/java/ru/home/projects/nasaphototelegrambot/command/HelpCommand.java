package ru.home.projects.nasaphototelegrambot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

public class HelpCommand implements Command {

    private final SendBotMessageService messageService;

    public HelpCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    public final static String HELP_MESSAGE = String.format("\uD83E\uDE90<b>Доступные команды</b>\uD83E\uDE90\n\n"
                    + "%s - получить фото дня, публикуемые Nasa\n"
                    + "%s - получить фото с марсохода (‼️Данный раздел находится в разработке ‼️)\n"
                    + "%s - подписаться/отписаться на ежедневную рассылку \"фото дня\" в 14:00 по мск\n"
                    + "%s - Помощь по командам и кнопкам\n\n",
            CommandName.APOD.getCommandName(), CommandName.MARS.getCommandName(),
            CommandName.SUBSCRIBE.getCommandName(), CommandName.HELP.getCommandName()
    );

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
