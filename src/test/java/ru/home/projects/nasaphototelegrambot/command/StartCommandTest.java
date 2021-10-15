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
public class StartCommandTest{

    protected NasaPhotoTelegramBot telegramBot = Mockito.mock(NasaPhotoTelegramBot.class);
    protected SendBotMessageService messageService = new SendBotMessageServiceImpl(telegramBot);
    protected TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);



    @Test
    public void shouldProperlyExecuteCommand() throws TelegramApiException {

        Long chatId = 1234567824356L;
        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(CommandName.START.getCommandName());
        Mockito.when(message.getText()).thenReturn(CommandName.START.getCommandName());
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(StartCommand.START_MESSAGE);
        sendMessage.setReplyMarkup(getReplyKeyboardMarkup());
        sendMessage.enableHtml(true);

        new StartCommand(messageService, telegramUserService).execute(update);

        Mockito.verify(telegramBot).execute(sendMessage);
    }

    private ReplyKeyboardMarkup getReplyKeyboardMarkup() {
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
