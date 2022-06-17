package ru.home.projects.nasaphototelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

import java.util.List;

public class AstronomyPictureOfTheDayCommand implements Command {

    private final SendBotMessageService messageService;

    public AstronomyPictureOfTheDayCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    public final static String PHOTO_MESSAGE = "Выберите \"<b>Фото сегодняшнего дня</b>\" чтобы получить актуальное на сегодня фото" +
            " или \"<b>Выбрать дату</b>\" для указания точной даты";

    @Override
    public void execute(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = getInlineKeyboardMarkup();
        messageService.sendMessage(update.getMessage().getChatId().toString(), PHOTO_MESSAGE, inlineKeyboardMarkup);
    }

    private InlineKeyboardMarkup getInlineKeyboardMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = List.of(List.of(
                InlineKeyboardButton.builder()
                        .text("Фото сегодняшнего дня")
                        .callbackData("today")
                        .build(),
                InlineKeyboardButton.builder()
                        .text("Выбрать дату")
                        .callbackData("DatePhoto")
                        .build()));
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }
}
