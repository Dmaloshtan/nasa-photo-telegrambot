package ru.home.projects.nasaphototelegrambot.nasaClient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarsPhoto {

    private Long id;
    private Long sol;
    private RoversCamera camera;
    @JsonProperty("img_src")
    private String urlPhoto;
    @JsonProperty("earth_date")
    private String earthDate;
    private Rover rover;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSol() {
        return sol;
    }

    public void setSol(Long sol) {
        this.sol = sol;
    }

    public RoversCamera getCamera() {
        return camera;
    }

    public void setCamera(RoversCamera camera) {
        this.camera = camera;
    }

    public Rover getRover() {
        return rover;
    }

    public void setRover(Rover rover) {
        this.rover = rover;
    }

    @Override
    public String toString() {
        return "MarsPhoto{" +
                "id=" + id +
                ", sol=" + sol +
                ", camera=" + camera +
                ", urlPhoto='" + urlPhoto + '\'' +
                ", earthDate='" + earthDate + '\'' +
                ", rover=" + rover +
                '}';
    }
}
