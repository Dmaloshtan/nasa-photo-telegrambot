package ru.home.projects.nasaphototelegrambot.handleCallback;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.bot.BotState;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;


public class PictureOfTheDayDifferentCallback implements ResponseCallbackQuery {

    private final SendBotMessageService messageService;

    private final TelegramUserService userService;

    public final static String SET_DATE_MESSAGE = "Введите дату, на которую хотите получить фото," +
            "\nв формате <b>YYYY-MM-DD</b>\n" +
            "Например: 2020-08-26\n" +
            "Самая ранняя возможная дата - 1995-06-16";

    public PictureOfTheDayDifferentCallback(SendBotMessageService messageService, TelegramUserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();

        userService.findByChatId(chatId).ifPresent(
                user -> {
                    user.setBotState(BotState.SET_DATE.getBotState());
                    userService.save(user);
                });

        String callBackId = update.getCallbackQuery().getId();
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery(callBackId);
        messageService.sendAnswerCallbackQuery(answerCallbackQuery);
        messageService.sendMessage(chatId, SET_DATE_MESSAGE);
    }
}
