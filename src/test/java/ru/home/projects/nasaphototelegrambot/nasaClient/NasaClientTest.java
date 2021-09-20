package ru.home.projects.nasaphototelegrambot.nasaClient;

import org.junit.jupiter.api.Test;
import ru.home.projects.nasaphototelegrambot.bot.NasaPhotoTelegramBot;
import ru.home.projects.nasaphototelegrambot.repository.entity.AstronomyPictureOfTheDay;

import static org.junit.jupiter.api.Assertions.*;

class NasaClientTest {

    @Test
    public void apiTest(){
        NasaClient nasaClient = new NasaClient();
//        nasaClient.setUrl("https://api.nasa.gov/planetary/apod?api_key=J8EKpXXYwrW1t3KMJaKPn0ON9k9AqgO0BiX8ue0g");

//        AstronomyPictureOfTheDay pictureOfTheDay = nasaClient.getAstronomyPictureOfTheDay();


        System.out.println(nasaClient.getAPI_KEY());

    }

}