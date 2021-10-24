package ru.home.projects.nasaphototelegrambot.nasaClient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoversCamera {

    private Long id;
    private String name;
    private Long rover_id;
    @JsonProperty("full_name")
    private String fullName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRover_id() {
        return rover_id;
    }

    public void setRover_id(Long rover_id) {
        this.rover_id = rover_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "RoversCamera{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rover_id=" + rover_id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
