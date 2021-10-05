package ru.home.projects.nasaphototelegrambot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.home.projects.nasaphototelegrambot.bot.BotState;
import ru.home.projects.nasaphototelegrambot.repository.entity.TelegramUser;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;

import java.util.ArrayList;
import java.util.List;

public class StartCommand implements Command {


    private final SendBotMessageService messageService;

    private final TelegramUserService telegramUserService;

    public final static String START_MESSAGE = "Привет. Я NasaPhoto Telegram Bot. " +
            "Я могу присылать фотографии космоса с сайта Nasa\n" +
            "Наберите комманду /help для получения инструкции";

    public StartCommand(SendBotMessageService messageService, TelegramUserService telegramUserService) {
        this.messageService = messageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    user.setSubscribe(false);
                    user.setBotState(BotState.DEFAULT.getBotState());
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setSubscribe(false);
                    telegramUser.setBotState(BotState.DEFAULT.getBotState());
                    telegramUser.setChatId(chatId);
                    telegramUserService.save(telegramUser);
                }
        );

        ReplyKeyboardMarkup keyboardMarkup = getReplyKeyboardMarkup();
        messageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE, keyboardMarkup);
    }

    private ReplyKeyboardMarkup getReplyKeyboardMarkup() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("photo");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("mars");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("subscribe");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("help");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }
}
