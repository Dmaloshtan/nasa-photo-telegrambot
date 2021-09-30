package ru.home.projects.nasaphototelegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClient;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDay;
import ru.home.projects.nasaphototelegrambot.repository.TelegramUserRepository;
import ru.home.projects.nasaphototelegrambot.repository.entity.TelegramUser;

import java.util.List;

@Service
public class SendPhotoOfTheDayImpl implements SendPhotoOfTheDayService{

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
        AstronomyPictureOfTheDay astronomyPictureOfTheDay = nasaClient.getAstronomyPictureOfTheDay();
        String message = String.format("%s\n\n" +
                "%s\n%s\n", astronomyPictureOfTheDay.getTitle(), astronomyPictureOfTheDay.getExplanation(), astronomyPictureOfTheDay.getUrl());
        List<TelegramUser> users = userRepository.findAllByActiveTrue();

        for(TelegramUser user : users){
            sendPhoto.sendMessage(user.getChatId(), message);
        }

    }
}
