package ru.home.projects.nasaphototelegrambot.nasaClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDayDto;
import ru.home.projects.nasaphototelegrambot.repository.entity.AstronomyPictureOfTheDay;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class NasaClient {

    @Value("${nasa.api_key}")
    private String API_KEY;

    private String url = "https://api.nasa.gov/planetary/apod?api_key=";

    public AstronomyPictureOfTheDay getAstronomyPictureOfTheDay(){
        String url = getUrl() + getAPI_KEY();
        RestTemplate template = new RestTemplate();

        AstronomyPictureOfTheDay astronomyPictureOfTheDay = template.getForObject(url,AstronomyPictureOfTheDay.class);

        return astronomyPictureOfTheDay;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAPI_KEY() {
        return API_KEY;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }
}
