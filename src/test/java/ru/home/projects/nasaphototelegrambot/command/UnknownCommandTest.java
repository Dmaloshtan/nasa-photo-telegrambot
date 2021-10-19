package ru.home.projects.nasaphototelegrambot.command;

import org.junit.jupiter.api.DisplayName;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@DisplayName("Unit-level testing for UnknownCommand")
public class UnknownCommandTest extends AbstractCommandTest{


    @Override
    String getCommandName() {
        return "/qweqwe";
    }

    @Override
    String getCommandMessage() {
        return UnknownCommand.UNKNOWN_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(messageService);
    }

    @Override
    ReplyKeyboardMarkup getReplyKeyboard() {
        return null;
    }
}
