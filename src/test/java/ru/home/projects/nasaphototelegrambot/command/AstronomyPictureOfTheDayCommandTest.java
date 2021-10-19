package ru.home.projects.nasaphototelegrambot.command;

import org.junit.jupiter.api.DisplayName;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-level testing for AstronomyPictureOfTheDayCommand")
class AstronomyPictureOfTheDayCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return CommandName.APOD.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return AstronomyPictureOfTheDayCommand.PHOTO_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new AstronomyPictureOfTheDayCommand(messageService);
    }

    @Override
    ReplyKeyboard getReplyKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("Фото сегодняшнего дня")
                                .callbackData("today")
                                .build(),
                        InlineKeyboardButton.builder()
                                .text("Выбрать дату")
                                .callbackData("DatePhoto")
                                .build())
        );
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

}