package ru.home.projects.nasaphototelegrambot.command;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.home.projects.nasaphototelegrambot.bot.NasaPhotoTelegramBot;
import ru.home.projects.nasaphototelegrambot.repository.entity.TelegramUser;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageServiceImpl;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;

import java.util.Optional;

import static org.mockito.Mockito.*;

public abstract class AbstractCommandTest {

    protected NasaPhotoTelegramBot telegramBot = mock(NasaPhotoTelegramBot.class);
    protected SendBotMessageService messageService = new SendBotMessageServiceImpl(telegramBot);
    @InjectMocks
    protected TelegramUserService telegramUserService;

    abstract String getCommandName();
    abstract String getCommandMessage();
    abstract Command getCommand();
    abstract ReplyKeyboard getReplyKeyboard();

    @Test
    public void shouldProperlyExecuteCommand() throws TelegramApiException {

        Long chatId = 1234567824356L;
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setChatId(chatId.toString());
        Update update = new Update();
        Message message = mock(Message.class);
        when(message.getChatId()).thenReturn(chatId);
        when(message.getText()).thenReturn(getCommandName());
        when(telegramUserService.findByChatId(anyString())).thenReturn(Optional.of(telegramUser));
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);
        sendMessage.setReplyMarkup(getReplyKeyboard());

        getCommand().execute(update);

        verify(telegramBot).execute(sendMessage);
    }

}
