package ru.home.projects.nasaphototelegrambot.command;

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

public abstract class AbstractCommandTest {

    protected NasaPhotoTelegramBot telegramBot = Mockito.mock(NasaPhotoTelegramBot.class);
    protected SendBotMessageService messageService = new SendBotMessageServiceImpl(telegramBot);
    protected TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);

    abstract String getCommandName();
    abstract String getCommandMessage();
    abstract Command getCommand();
    abstract ReplyKeyboardMarkup getReplyKeyboardMarkUp();

    @Test
    public void shouldProperlyExecuteCommand() throws TelegramApiException {

        Long chatId = 1234567824356L;
        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);
        sendMessage.setReplyMarkup(getReplyKeyboardMarkUp());

        getCommand().execute(update);

        Mockito.verify(telegramBot).execute(sendMessage);
    }

}
