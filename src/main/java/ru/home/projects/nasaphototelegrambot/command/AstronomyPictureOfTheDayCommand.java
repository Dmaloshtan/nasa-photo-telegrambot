package ru.home.projects.nasaphototelegrambot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClient;
import ru.home.projects.nasaphototelegrambot.repository.entity.AstronomyPictureOfTheDay;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

public class AstronomyPictureOfTheDayCommand implements Command{

    @Autowired
    private final SendBotMessageService messageService;
    @Autowired
    private final NasaClient nasaClient;

    public AstronomyPictureOfTheDayCommand(SendBotMessageService messageService, NasaClient nasaClient) {
        this.messageService = messageService;
        this.nasaClient = nasaClient;
    }

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId().toString(), sendAstronomyPictureOfTheDay());
    }

    private String sendAstronomyPictureOfTheDay(){
        AstronomyPictureOfTheDay astronomyPictureOfTheDay = nasaClient.getAstronomyPictureOfTheDay();
        String message = String.format("%s\n\n" +
                "%s\n%s\n", astronomyPictureOfTheDay.getTitle(), astronomyPictureOfTheDay.getExplanation(), astronomyPictureOfTheDay.getUrl());
        return message;
    }
}
