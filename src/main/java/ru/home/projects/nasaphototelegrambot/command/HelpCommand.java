package ru.home.projects.nasaphototelegrambot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

public class HelpCommand implements Command {

    private final SendBotMessageService messageService;

    public HelpCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    public final static String HELP_MESSAGE = String.format("✨<b>Доступные команды</b>✨\n\n"
                    + "<b>Начать\\закончить работу с ботом</b>\n"
                    + "%s - начать работу со мной\n"
                    + "%s - приостановить работу со мной\n\n"
                    + "%s - получить помощь в работе со мной\n\n"
                    + "<b>Получить фото</b>\n"
                    + "%s - Получить фото сегодняшнего дня\n"
                    + "%s + Дата в формате \"YYYY-MM-DD\", например \"/apod 2020-11-25\"  - Получить фото дня на выбранную дату\n",
            CommandName.START.getCommandName(), CommandName.STOP.getCommandName(), CommandName.HELP.getCommandName(),
            CommandName.APOD.getCommandName(), CommandName.APOD.getCommandName());

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
