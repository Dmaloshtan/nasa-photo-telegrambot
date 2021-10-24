package ru.home.projects.nasaphototelegrambot.nasaClient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarsPhoto {

    @JsonProperty("img_src")
    private String urlPhoto;
    @JsonProperty("earth_date")
    private String earthDate;

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getEarthDate() {
        return earthDate;
    }

    public void setEarthDate(String earthDate) {
        this.earthDate = earthDate;
    }

    @Override
    public String toString() {
        return "MarsPhoto{" +
                "urlPhoto='" + urlPhoto + '\'' +
                ", earthDate='" + earthDate + '\'' +
                '}';
    }
}
