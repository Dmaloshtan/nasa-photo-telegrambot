package ru.home.projects.nasaphototelegrambot.handleCallback;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.command.Command;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClient;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDay;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;


public class PictureOfTheDayCallback implements ResponseCallbackQuery {

    private final SendBotMessageService messageService;

    private final NasaClient nasaClient;

    public PictureOfTheDayCallback(SendBotMessageService messageService, NasaClient nasaClient) {
        this.messageService = messageService;
        this.nasaClient = nasaClient;
    }

    @Override
    public void execute(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        String callBackId = update.getCallbackQuery().getId();
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery(callBackId);
        messageService.sendAnswerCallbackQuery(answerCallbackQuery);
        messageService.sendMessage(chatId.toString(), sendAstronomyPictureOfTheDay());
    }

    private String sendAstronomyPictureOfTheDay() {
        AstronomyPictureOfTheDay astronomyPictureOfTheDay = nasaClient.getAstronomyPictureOfTheDay();
        String message = String.format("%s\n\n" +
                "%s\n%s\n", astronomyPictureOfTheDay.getTitle(), astronomyPictureOfTheDay.getExplanation(), astronomyPictureOfTheDay.getUrl());
        return message;
    }



    //TODO Добавить методы для обработки некорректных сообщений (не верная дата, не верный формат даты, на текущую дату нет фото)

}
