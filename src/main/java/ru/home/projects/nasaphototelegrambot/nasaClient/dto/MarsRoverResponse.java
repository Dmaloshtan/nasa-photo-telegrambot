package ru.home.projects.nasaphototelegrambot.nasaClient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class MarsRoverResponse {

    @JsonProperty("photos")
    List<MarsPhoto> marsPhotos = new ArrayList<>();

    public List<MarsPhoto> getMarsPhotos() {
        return marsPhotos;
    }

    public void setMarsPhotos(List<MarsPhoto> marsPhotos) {
        this.marsPhotos = marsPhotos;
    }

    @Override
    public String toString() {
        return "MarsRoverResponse{" +
                "marsPhotos=" + marsPhotos +
                '}';
    }
}
