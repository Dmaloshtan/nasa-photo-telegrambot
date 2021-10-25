package ru.home.projects.nasaphototelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarsCommand implements Command {

    private final SendBotMessageService messageService;

    public MarsCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    public final static String MARS_MESSAGE = "Выберите марсоход, с которого хотите получить фото.\n" +
            "Загрузка может занять <b>3-5 секунд</b>, т.к. в чат загружается сразу галерея из 10 фотографий. " +
            "Просьба подождать.";

    @Override
    public void execute(Update update) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("Curiosity")
                                .callbackData("curiosity")
                                .build(),
                        InlineKeyboardButton.builder()
                                .text("Opportunity")
                                .callbackData("opportunity")
                                .build(),
                        InlineKeyboardButton.builder()
                                .text("Spirit")
                                .callbackData("spirit")
                                .build())
        );
        inlineKeyboardMarkup.setKeyboard(keyboard);
        messageService.sendMessage(update.getMessage().getChatId().toString(), MARS_MESSAGE, inlineKeyboardMarkup);

    }
}
