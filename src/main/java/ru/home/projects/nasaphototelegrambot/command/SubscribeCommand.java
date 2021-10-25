package ru.home.projects.nasaphototelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubscribeCommand implements Command {

    private final SendBotMessageService messageService;

    public SubscribeCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    public final static String SUBSCRIBE_MESSAGE = "Нажав кнопку <b>\"Подписаться\"</b> вам будет каждый день в 14:00 по мск приходить фото дня.\n" +
                    "Кнопка \"Отменить подписку\" отменяет данную подписку";

    @Override
    public void execute(Update update) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("Подписаться")
                                .callbackData("subscribe")
                                .build(),
                        InlineKeyboardButton.builder()
                                .text("Отменить подписку")
                                .callbackData("unsubscribe")
                                .build())
        );
        inlineKeyboardMarkup.setKeyboard(keyboard);

        messageService.sendMessage(update.getMessage().getChatId().toString(), SUBSCRIBE_MESSAGE, inlineKeyboardMarkup);
    }
}
