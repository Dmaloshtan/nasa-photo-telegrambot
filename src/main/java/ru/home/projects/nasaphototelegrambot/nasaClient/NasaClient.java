package ru.home.projects.nasaphototelegrambot.nasaClient;

import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDay;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.MarsPhoto;

public interface NasaClient {

    AstronomyPictureOfTheDay getAstronomyPictureOfTheDay();

    AstronomyPictureOfTheDay getAstronomyPictureOfTheDifferentDay(String date);

    MarsPhoto getMarsPhoto(String rover);


}
