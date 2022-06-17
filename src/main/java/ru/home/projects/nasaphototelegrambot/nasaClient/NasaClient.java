package ru.home.projects.nasaphototelegrambot.nasaClient;

import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDay;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.MarsPhoto;

import java.util.List;

public interface NasaClient {

    AstronomyPictureOfTheDay getAstronomyPictureOfTheDay();

    AstronomyPictureOfTheDay getAstronomyPictureOfTheDifferentDay(String date);

    List<MarsPhoto> getMarsPhotos(String rover);
}
