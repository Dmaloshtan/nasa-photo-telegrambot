package ru.home.projects.nasaphototelegrambot.command;

import org.junit.jupiter.api.DisplayName;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-level testing for MarsCommand")
class MarsCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return CommandName.MARS.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return MarsCommand.MARS_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new MarsCommand(messageService);
    }

    @Override
    ReplyKeyboardMarkup getReplyKeyboard() {
        return null;
    }
}