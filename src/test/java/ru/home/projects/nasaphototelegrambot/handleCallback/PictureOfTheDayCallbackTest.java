package ru.home.projects.nasaphototelegrambot.handleCallback;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.home.projects.nasaphototelegrambot.bot.NasaPhotoTelegramBot;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClient;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDay;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageServiceImpl;

@DisplayName("Unit-level testing for PictureOfTheDayCallback")
public class PictureOfTheDayCallbackTest {

    private NasaPhotoTelegramBot telegramBot = Mockito.mock(NasaPhotoTelegramBot.class);
    private SendBotMessageService messageService = new SendBotMessageServiceImpl(telegramBot);
    private AstronomyPictureOfTheDay astronomyPictureOfTheDay;
    private NasaClient nasaClient = Mockito.mock(NasaClient.class);
    private PictureOfTheDayCallback pictureOfTheDayCallback = new PictureOfTheDayCallback(messageService, nasaClient);

    @Test
    void ShouldSendAstronomyPictureOfTheDay() {
        astronomyPictureOfTheDay = new AstronomyPictureOfTheDay();
        astronomyPictureOfTheDay.setDate("2011-10-05");
        astronomyPictureOfTheDay.setExplanation("SomeExplanation");
        astronomyPictureOfTheDay.setMediaType("Photo");
        astronomyPictureOfTheDay.setTitle("SomeTitle");
        astronomyPictureOfTheDay.setUrl("setUrl");

        String resultString = String.format("%s\n\n" +
                        "%s\n%s\n", astronomyPictureOfTheDay.getTitle(),
                astronomyPictureOfTheDay.getExplanation(),
                astronomyPictureOfTheDay.getUrl());

        Mockito.when(nasaClient.getAstronomyPictureOfTheDay()).thenReturn(astronomyPictureOfTheDay);

        Assertions.assertEquals(pictureOfTheDayCallback.sendAstronomyPictureOfTheDay(), resultString);
    }

}
