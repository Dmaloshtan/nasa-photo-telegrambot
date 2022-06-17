package ru.home.projects.nasaphototelegrambot.command;

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

    public final static String START_MESSAGE = "Привет. Я NasaPhoto Telegram Bot \uD83D\uDEF0\n" +
            "Я могу присылать фотографии космоса с сайта Nasa\uD83C\uDF0E\n" +
            "Чтобы воспользоваться моим функционалом, нажми нужную кнопку на появившейся клавиатуре.\n" +
            "Если хочешь узнать подробнее о каждой кнопке, то нажми <b>help</b>";

    public StartCommand(SendBotMessageService messageService, TelegramUserService telegramUserService) {
        this.messageService = messageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String username = update.getMessage().getFrom().getUserName();
        createUser(chatId, username);

        ReplyKeyboardMarkup keyboardMarkup = getReplyKeyboardMarkup();
        messageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE, keyboardMarkup);
    }

    private void createUser(String chatId, String username) {
        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    user.setBotState(BotState.DEFAULT.getBotState());
                    user.setUsername(username);
                    telegramUserService.save(user);
                },
                () -> {
                    TelegramUser telegramUser = new TelegramUser();
                    telegramUser.setActive(true);
                    telegramUser.setSubscribe(false);
                    telegramUser.setBotState(BotState.DEFAULT.getBotState());
                    telegramUser.setChatId(chatId);
                    telegramUser.setUsername(username);
                    telegramUserService.save(telegramUser);
                }
        );
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
