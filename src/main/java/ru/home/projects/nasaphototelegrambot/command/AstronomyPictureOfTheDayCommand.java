package ru.home.projects.nasaphototelegrambot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClientImpl;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDay;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.GroupRequestArgs;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

public class AstronomyPictureOfTheDayCommand implements Command{


    private final SendBotMessageService messageService;

    private final NasaClientImpl nasaClient;

    public AstronomyPictureOfTheDayCommand(SendBotMessageService messageService, NasaClientImpl nasaClient) {
        this.messageService = messageService;
        this.nasaClient = nasaClient;
    }

    @Override
    public void execute(Update update) {
        String[] message = update.getMessage().getText().split(" ");
        System.out.println(update.getMessage().getText());

        String date = null;

        if(message.length > 1){
            date = message[1];
            messageService.sendMessage(update.getMessage().getChatId().toString(), sendAstronomyPictureOfTheDifferentDay(date));
        } else {
            messageService.sendMessage(update.getMessage().getChatId().toString(), sendAstronomyPictureOfTheDay());
        }
    }

    private String sendAstronomyPictureOfTheDay(){
        AstronomyPictureOfTheDay astronomyPictureOfTheDay = nasaClient.getAstronomyPictureOfTheDay();
        String message = String.format("%s\n\n" +
                "%s\n%s\n", astronomyPictureOfTheDay.getTitle(), astronomyPictureOfTheDay.getExplanation(), astronomyPictureOfTheDay.getUrl());
        return message;
    }

    private String sendAstronomyPictureOfTheDifferentDay(String date){
        AstronomyPictureOfTheDay astronomyPictureOfTheDay = nasaClient.getAstronomyPictureOfTheDifferentDay(date);
        String message = String.format("%s\n\n" +
                "%s\n%s\n", astronomyPictureOfTheDay.getTitle(), astronomyPictureOfTheDay.getExplanation(), astronomyPictureOfTheDay.getUrl());
        return message;
    }

}
