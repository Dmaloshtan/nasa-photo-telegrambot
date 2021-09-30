package ru.home.projects.nasaphototelegrambot.nasaClient;

import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDay;

public interface NasaClient {

    public AstronomyPictureOfTheDay getAstronomyPictureOfTheDay();

    public AstronomyPictureOfTheDay getAstronomyPictureOfTheDifferentDay(String date);
}
