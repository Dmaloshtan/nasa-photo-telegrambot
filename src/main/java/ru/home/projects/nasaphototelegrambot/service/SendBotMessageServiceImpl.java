package ru.home.projects.nasaphototelegrambot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.home.projects.nasaphototelegrambot.bot.NasaPhotoTelegramBot;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService{


    private final NasaPhotoTelegramBot nasaBot;

    @Autowired
    public SendBotMessageServiceImpl(NasaPhotoTelegramBot nasaBot) {
        this.nasaBot = nasaBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            nasaBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
