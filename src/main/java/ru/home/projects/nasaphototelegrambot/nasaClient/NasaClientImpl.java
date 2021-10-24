package ru.home.projects.nasaphototelegrambot.nasaClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDay;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.MarsPhoto;

@Service
public class NasaClientImpl implements NasaClient {

    private final String urlPhotoOfTheDay;
    private final String urlMarsPhoto;
    private final String API_KEY;

    public NasaClientImpl(@Value("${nasa.api_key}") String nasaApiKey) {
        this.urlPhotoOfTheDay = "https://api.nasa.gov/planetary/apod?api_key=" + nasaApiKey;
        this.urlMarsPhoto = "https://api.nasa.gov/mars-photos/api/v1/rovers/";
        this.API_KEY = nasaApiKey;
    }

    @Override
    public AstronomyPictureOfTheDay getAstronomyPictureOfTheDay() {
        RestTemplate template = new RestTemplate();
        AstronomyPictureOfTheDay astronomyPictureOfTheDay = template.getForObject(urlPhotoOfTheDay, AstronomyPictureOfTheDay.class);
        return astronomyPictureOfTheDay;
    }

    @Override
    public AstronomyPictureOfTheDay getAstronomyPictureOfTheDifferentDay(String date) {
        String urlWithDate = urlPhotoOfTheDay + "&date=" + date;
        RestTemplate template = new RestTemplate();
        AstronomyPictureOfTheDay astronomyPictureOfTheDay = template.getForObject(urlWithDate, AstronomyPictureOfTheDay.class);
        return astronomyPictureOfTheDay;
    }

    @Override
    public MarsPhoto getMarsPhoto(String rover) {
        String finalUrl = urlMarsPhoto + rover + "/photos";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(finalUrl)
                .queryParam("sol", "1000")
                .queryParam("api_key", API_KEY);
        RestTemplate template = new RestTemplate();
        MarsPhoto marsPhoto = template.getForObject(builder.toUriString(), MarsPhoto.class);
        return marsPhoto;
    }


}
