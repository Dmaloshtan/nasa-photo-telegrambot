package ru.home.projects.nasaphototelegrambot.handleMessage;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.bot.BotState;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClient;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDay;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.ExceptionNasaServer;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;


public class AstronomyPictureOfTheDifferentDay implements ResponseMessage {

    private final SendBotMessageService messageService;

    private final NasaClient nasaClient;

    private final TelegramUserService userService;

    public AstronomyPictureOfTheDifferentDay(SendBotMessageService messageService, NasaClient nasaClient, TelegramUserService userService) {
        this.messageService = messageService;
        this.nasaClient = nasaClient;
        this.userService = userService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String date = update.getMessage().getText();

        try {
            String photo = sendAstronomyPictureOfTheDifferentDay(date);
            messageService.sendMessage(update.getMessage().getChatId().toString(), photo);

            userService.findByChatId(chatId).ifPresent(
                    user -> {
                        user.setBotState(BotState.DEFAULT.getBotState());
                        userService.save(user);
                    });
        } catch (HttpClientErrorException ex) {
            Gson gson = new Gson();
            ExceptionNasaServer exceptionNasaServer = gson.fromJson(ex.getResponseBodyAsString(), ExceptionNasaServer.class);
            messageService.sendMessage(update.getMessage().getChatId().toString(), "Вы ввели не верный формат даты или такой даты не существует, ответ сервера:\n" +
                    "<b>" + exceptionNasaServer.getMsg() + "</b>");

        }
    }

    private String sendAstronomyPictureOfTheDifferentDay(String date) {
        AstronomyPictureOfTheDay astronomyPictureOfTheDay = nasaClient.getAstronomyPictureOfTheDifferentDay(date);
        String message = String.format("%s\n\n" +
                "%s\n%s\n", astronomyPictureOfTheDay.getTitle(), astronomyPictureOfTheDay.getExplanation(), astronomyPictureOfTheDay.getUrl());
        return message;
    }
}
