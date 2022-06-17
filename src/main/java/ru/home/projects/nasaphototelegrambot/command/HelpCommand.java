package ru.home.projects.nasaphototelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.utils.AnswerMessage;

public class HelpCommand implements Command {

    private final SendBotMessageService messageService;

    public HelpCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId().toString(), String.format(AnswerMessage.HELP_COMMAND,
                CommandName.APOD.getCommandName(), CommandName.MARS.getCommandName(),
                CommandName.SUBSCRIBE.getCommandName(), CommandName.HELP.getCommandName()
        ));
    }
}
