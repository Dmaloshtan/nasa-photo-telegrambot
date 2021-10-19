package ru.home.projects.nasaphototelegrambot.command;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.home.projects.nasaphototelegrambot.bot.NasaPhotoTelegramBot;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageServiceImpl;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Unit-level testing for StartCommand")
public class StartCommandTest extends AbstractCommandTest{


    @Override
    String getCommandName() {
        return CommandName.START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return StartCommand.START_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StartCommand(messageService, telegramUserService);
    }

    @Override
    ReplyKeyboardMarkup getReplyKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("photo");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("mars");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("subscribe");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("help");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }
}
