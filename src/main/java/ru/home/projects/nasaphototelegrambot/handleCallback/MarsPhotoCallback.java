package ru.home.projects.nasaphototelegrambot.handleCallback;

import com.google.gson.Gson;
import org.springframework.web.client.HttpClientErrorException;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClient;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.ExceptionNasaServer;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.MarsPhoto;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarsPhotoCallback implements ResponseCallbackQuery {

    private final SendBotMessageService messageService;
    private final NasaClient nasaClient;

    public MarsPhotoCallback(SendBotMessageService messageService, NasaClient nasaClient) {
        this.messageService = messageService;
        this.nasaClient = nasaClient;
    }

    @Override
    public void execute(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        String rover = update.getCallbackQuery().getData();
        String callBackId = update.getCallbackQuery().getId();
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery(callBackId);
        try {
            List<MarsPhoto> photos = nasaClient.getMarsPhotos(rover);
            String datePhoto = photos.get(0).getEarthDate();
            String roverName = photos.get(0).getRover().getName();
            String message = String.format("Марсоход - %s\nДата съёмки - %s\n", roverName, datePhoto);

            List<InputMedia> inputMedia = new ArrayList<>();
            Random random = new Random();

            for (int i = 0; i < 10; i++) {
                InputMediaPhoto inputMediaPhoto = new InputMediaPhoto();
                MarsPhoto marsPhoto = photos.get(random.nextInt(photos.size() - 1));
                inputMediaPhoto.setMedia(marsPhoto.getUrlPhoto());
                inputMedia.add(inputMediaPhoto);
            }
            inputMedia.get(0).setCaption(message);
            messageService.sendPhotoGroup(chatId.toString(), inputMedia);
        } catch (HttpClientErrorException ex) {
            Gson gson = new Gson();
            ExceptionNasaServer exceptionNasaServer = gson.fromJson(ex.getResponseBodyAsString(), ExceptionNasaServer.class);
            messageService.sendMessage(update.getCallbackQuery().getMessage().getChatId().toString(), "Ошибка сервера, на сайте Nasa ведутся технические работы:\n" +
                    "<b>" + exceptionNasaServer.getMsg() + "</b>");
        }
        messageService.sendAnswerCallbackQuery(answerCallbackQuery);
    }
}
