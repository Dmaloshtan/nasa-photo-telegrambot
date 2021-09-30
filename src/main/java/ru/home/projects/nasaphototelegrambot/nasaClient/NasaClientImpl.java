package ru.home.projects.nasaphototelegrambot.nasaClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDay;

@Service
public class NasaClientImpl implements NasaClient{

    private final String url;

    public NasaClientImpl(@Value("${nasa.api_key}") String nasaApiKey) {
        this.url = "https://api.nasa.gov/planetary/apod?api_key=" + nasaApiKey;
    }

    public AstronomyPictureOfTheDay getAstronomyPictureOfTheDay(){
        RestTemplate template = new RestTemplate();
        AstronomyPictureOfTheDay astronomyPictureOfTheDay = template.getForObject(url,AstronomyPictureOfTheDay.class);
        return astronomyPictureOfTheDay;
    }


    @Override
    public AstronomyPictureOfTheDay getAstronomyPictureOfTheDifferentDay(String date) {
        String urlWithDate = url + "&date=" + date;
        RestTemplate template = new RestTemplate();
        AstronomyPictureOfTheDay astronomyPictureOfTheDay = template.getForObject(urlWithDate,AstronomyPictureOfTheDay.class);
        return astronomyPictureOfTheDay;
    }


}
