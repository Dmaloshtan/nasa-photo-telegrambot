package ru.home.projects.nasaphototelegrambot.handleCallback;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.bot.BotState;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;
import ru.home.projects.nasaphototelegrambot.utils.AnswerMessage;


public class PictureOfTheDayDifferentCallback implements ResponseCallbackQuery {

    private final SendBotMessageService messageService;

    private final TelegramUserService userService;

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
        messageService.sendMessage(chatId, AnswerMessage.ASTRONOMY_PICTURE_OF_THE_DAY_CALLBACK);
    }
}
