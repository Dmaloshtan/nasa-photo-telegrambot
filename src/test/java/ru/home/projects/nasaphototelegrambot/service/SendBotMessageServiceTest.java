package ru.home.projects.nasaphototelegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.home.projects.nasaphototelegrambot.bot.NasaPhotoTelegramBot;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-level testing for SendBotMessageService")
class SendBotMessageServiceTest {

    private SendBotMessageService messageService;
    private NasaPhotoTelegramBot telegramBot;

    @BeforeEach
    public void init(){
        telegramBot = Mockito.mock(NasaPhotoTelegramBot.class);
        messageService = new SendBotMessageServiceImpl(telegramBot);
    }

    @Test
    public void shouldProperlySendMessage() throws TelegramApiException {
        //given
        String chatId = "test_chat_id";
        String message = "test_message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);

        //when
        messageService.sendMessage(chatId, message);

        //then
        Mockito.verify(telegramBot).execute(sendMessage);
    }

    @Test
    public void shouldProperlySendMessageWithMarkup() throws TelegramApiException {
        //given
        String chatId = "test_chat_id";
        String message = "test_message";
        ReplyKeyboardMarkup keyboardMarkup = Mockito.mock(ReplyKeyboardMarkup.class);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setReplyMarkup(keyboardMarkup);

        //when
        messageService.sendMessage(chatId, message, keyboardMarkup);

        //then
        Mockito.verify(telegramBot).execute(sendMessage);
    }

}