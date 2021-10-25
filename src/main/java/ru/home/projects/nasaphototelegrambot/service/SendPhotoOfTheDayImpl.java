package ru.home.projects.nasaphototelegrambot.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClient;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDay;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.ExceptionNasaServer;
import ru.home.projects.nasaphototelegrambot.repository.TelegramUserRepository;
import ru.home.projects.nasaphototelegrambot.repository.entity.TelegramUser;

import java.util.List;

@Service
public class SendPhotoOfTheDayImpl implements SendPhotoOfTheDayService {

    private final NasaClient nasaClient;
    private final SendBotMessageService sendPhoto;
    private final TelegramUserRepository userRepository;

    @Autowired
    public SendPhotoOfTheDayImpl(NasaClient nasaClient, SendBotMessageService sendPhoto, TelegramUserRepository userRepository) {
        this.nasaClient = nasaClient;
        this.sendPhoto = sendPhoto;
        this.userRepository = userRepository;
    }

    @Override
    public void sendPhoto() {
        List<TelegramUser> users = userRepository.findAllBySubscribeTrue();
        try{
            AstronomyPictureOfTheDay astronomyPictureOfTheDay = nasaClient.getAstronomyPictureOfTheDay();
            String message = String.format("<b>Рассылка фото дня</b>\n\n" +
                    "%s\n\n" +
                    "%s\n%s\n", astronomyPictureOfTheDay.getTitle(), astronomyPictureOfTheDay.getExplanation(), astronomyPictureOfTheDay.getUrl());
            for (TelegramUser user : users) {
                sendPhoto.sendMessage(user.getChatId(), message);
            }
        } catch (HttpClientErrorException ex){
            Gson gson = new Gson();
            ExceptionNasaServer exceptionNasaServer = gson.fromJson(ex.getResponseBodyAsString(), ExceptionNasaServer.class);
            for (TelegramUser user : users) {
                sendPhoto.sendMessage(user.getChatId(), "Прошу прощения, но на данный момент сервер Nasa не доступен," +
                        " в связи с чем рассылка \"Фото дня\" не возможна. ☹️" +
                        "Попробуйте позже самостоятельно запросить фотографию, через соответствующую команду бота:\n" +
                        "<b>" + exceptionNasaServer.getMsg()+ "</b>");
            }
        }
    }
}
