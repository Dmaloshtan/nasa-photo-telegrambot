package ru.home.projects.nasaphototelegrambot.command;

import org.junit.jupiter.api.DisplayName;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@DisplayName("Unit-level testing for StatCommand")
public class StatCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return CommandName.STAT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return String.format(StatCommand.STAT_MESSAGE, 0);
    }

    @Override
    Command getCommand() {
        return new StatCommand(messageService, telegramUserService);
    }

    @Override
    ReplyKeyboardMarkup getReplyKeyboard() {
        return null;
    }
}
