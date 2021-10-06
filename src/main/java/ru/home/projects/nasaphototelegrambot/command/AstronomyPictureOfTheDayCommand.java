package ru.home.projects.nasaphototelegrambot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClient;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClientImpl;
import ru.home.projects.nasaphototelegrambot.nasaClient.dto.AstronomyPictureOfTheDay;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AstronomyPictureOfTheDayCommand implements Command{

    private final SendBotMessageService messageService;

    public AstronomyPictureOfTheDayCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                        .text("Фото сегодняшнего дня")
                        .callbackData("today")
                        .build(),
                        InlineKeyboardButton.builder()
                                .text("Выбрать дату")
                                .callbackData("DatePhoto")
                                .build())
                );
        inlineKeyboardMarkup.setKeyboard(keyboard);
        messageService.sendMessage(update.getMessage().getChatId().toString(), "Выберите \"<b>Фото сегодняшнего дня</b>\" чтобы получить актуальное на сегодня фото" +
                " или \"<b>выбрать дату</b>\" для указания точной даты", inlineKeyboardMarkup);

    }



}
