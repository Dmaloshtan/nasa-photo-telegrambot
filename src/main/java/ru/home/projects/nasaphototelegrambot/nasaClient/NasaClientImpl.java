package ru.home.projects.nasaphototelegrambot.nasaClient;

import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDay;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.MarsPhoto;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.MarsRoverResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public List<MarsPhoto> getMarsPhotos(String rover) {
        String finalUrl = urlMarsPhoto + rover + "/photos";

        RestTemplate template = new RestTemplate();
        MarsRoverResponse marsRoverResponse;

        do {
            int sol = getRandomSol(rover);
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(finalUrl)
                    .queryParam("sol", String.valueOf(sol))
                    .queryParam("api_key", API_KEY);
            marsRoverResponse = template.getForObject(builder.toUriString(), MarsRoverResponse.class);
        } while (marsRoverResponse.getMarsPhotos().size() == 0);

        List<MarsPhoto> marsPhotos = new ArrayList<>();

        for(MarsPhoto photo : marsRoverResponse.getMarsPhotos()){
            marsPhotos.add(photo);
        }

        return marsPhotos;
    }

    private int getRandomSol(String rover){
        Random random = new Random();
        int randomSol = 1;

        switch (rover){
            case "curiosity" :
                randomSol = random.nextInt(RoversSol.CURIOSITY_ROVER_MAX_SOL.getRoversSol()
                        +RoversSol.CURIOSITY_ROVER_MIN_SOL.getRoversSol());
                break;
            case "opportunity" :
                randomSol = random.nextInt(RoversSol.OPPORTUNITY_ROVER_MAX_SOL.getRoversSol()
                        +RoversSol.OPPORTUNITY_ROVER_MIN_SOL.getRoversSol());
                break;
            case "spirit" :
                randomSol = random.nextInt(RoversSol.SPIRIT_ROVER_MAX_SOL.getRoversSol()
                        +RoversSol.SPIRIT_ROVER_MIN_SOL.getRoversSol());
                break;
        }
        return randomSol;
    }


}
