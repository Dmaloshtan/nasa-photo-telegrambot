package ru.home.projects.nasaphototelegrambot.nasaClient;

import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDay;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.GroupRequestArgs;

public interface NasaClient {

    public AstronomyPictureOfTheDay getAstronomyPictureOfTheDay();

    public AstronomyPictureOfTheDay getAstronomyPictureOfTheDifferentDay(String date);
}
