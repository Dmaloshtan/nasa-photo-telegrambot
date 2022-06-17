package ru.home.projects.nasaphototelegrambot.handleCallback;

import com.google.gson.Gson;
import org.springframework.web.client.HttpClientErrorException;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClient;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDay;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.ExceptionNasaServer;
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
        try{
            String photo = sendAstronomyPictureOfTheDay();
            messageService.sendMessage(chatId.toString(), photo);
        } catch (HttpClientErrorException ex){
            Gson gson = new Gson();
            ExceptionNasaServer exceptionNasaServer = gson.fromJson(ex.getResponseBodyAsString(), ExceptionNasaServer.class);
            messageService.sendMessage(update.getMessage().getChatId().toString(), "Ошибка сервера, на сайте Nasa ведутся технические работы:\n" +
                    "<b>" + exceptionNasaServer.getMsg()+ "</b>");
        }
    }

    public String sendAstronomyPictureOfTheDay() {
        AstronomyPictureOfTheDay astronomyPictureOfTheDay = nasaClient.getAstronomyPictureOfTheDay();
        return String.format("%s\n\n" +
                "%s\n%s\n", astronomyPictureOfTheDay.getTitle(), astronomyPictureOfTheDay.getExplanation(), astronomyPictureOfTheDay.getUrl());
    }
}
