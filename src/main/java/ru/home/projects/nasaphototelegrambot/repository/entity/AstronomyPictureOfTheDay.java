package ru.home.projects.nasaphototelegrambot.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AstronomyPictureOfTheDay {

    private String date;
    private String explanation;
    @JsonProperty("media_type")
    private String mediaType;
    private String title;
    private String url;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "AstronomyPictureOfTheDay{" +
                "date='" + date + '\'' +
                ", explanation='" + explanation + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
