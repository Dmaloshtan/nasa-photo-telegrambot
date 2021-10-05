package ru.home.projects.nasaphototelegrambot.handleMessage;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.bot.BotState;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClient;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDay;
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

        userService.findByChatId(chatId).ifPresent(
                user -> {
                    user.setBotState(BotState.DEFAULT.getBotState());
                    userService.save(user);
                });

        messageService.sendMessage(update.getMessage().getChatId().toString(), sendAstronomyPictureOfTheDifferentDay(date));
    }

    private String sendAstronomyPictureOfTheDifferentDay(String date) {
        AstronomyPictureOfTheDay astronomyPictureOfTheDay = nasaClient.getAstronomyPictureOfTheDifferentDay(date);
        String message = String.format("%s\n\n" +
                "%s\n%s\n", astronomyPictureOfTheDay.getTitle(), astronomyPictureOfTheDay.getExplanation(), astronomyPictureOfTheDay.getUrl());
        return message;
    }
}
