package ru.home.projects.nasaphototelegrambot.job;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.home.projects.nasaphototelegrambot.service.SendPhotoOfTheDayService;

import java.time.LocalDateTime;

@Component
public class SendPhotoOfTheDayJob {

    private SendPhotoOfTheDayService photoOfTheDayService;

    @Autowired
    public SendPhotoOfTheDayJob(SendPhotoOfTheDayService photoOfTheDayService) {
        this.photoOfTheDayService = photoOfTheDayService;
    }

    @Scheduled(cron = "${job.cron.rate}")
    public void sendPhotoOfTheDay(){
        photoOfTheDayService.sendPhoto();
    }
}
