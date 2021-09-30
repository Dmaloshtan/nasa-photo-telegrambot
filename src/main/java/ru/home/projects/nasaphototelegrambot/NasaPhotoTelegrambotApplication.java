package ru.home.projects.nasaphototelegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NasaPhotoTelegrambotApplication {

    public static void main(String[] args) {
        SpringApplication.run(NasaPhotoTelegrambotApplication.class, args);
    }

}
