package ru.home.projects.nasaphototelegrambot.command;

import org.junit.jupiter.api.DisplayName;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@DisplayName("Unit-level testing for NoCommand")
public class NoCommandTest extends AbstractCommandTest{


    @Override
    String getCommandName() {
        return CommandName.NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NoCommand.NO_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoCommand(messageService);
    }

    @Override
    ReplyKeyboardMarkup getReplyKeyboard() {
        return null;
    }
}
