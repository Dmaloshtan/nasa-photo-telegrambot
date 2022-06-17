package ru.home.projects.nasaphototelegrambot.command;

import org.junit.jupiter.api.DisplayName;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    ReplyKeyboard getReplyKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("Curiosity")
                                .callbackData("curiosity")
                                .build(),
                        InlineKeyboardButton.builder()
                                .text("Opportunity")
                                .callbackData("opportunity")
                                .build(),
                        InlineKeyboardButton.builder()
                                .text("Spirit")
                                .callbackData("spirit")
                                .build())
        );
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }
}