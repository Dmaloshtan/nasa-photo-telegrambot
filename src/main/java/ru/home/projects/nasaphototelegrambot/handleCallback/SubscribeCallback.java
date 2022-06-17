package ru.home.projects.nasaphototelegrambot.handleCallback;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;

public class SubscribeCallback implements ResponseCallbackQuery {

    private final SendBotMessageService messageService;

    private final TelegramUserService userService;

    public final static String SUBSCRIBE_MESSAGE = "Вы подписаны на фото дня";

    public SubscribeCallback(SendBotMessageService messageService, TelegramUserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();

        userService.findByChatId(chatId).ifPresent(
                user -> {
                    user.setSubscribe(true);
                    userService.save(user);
                });

        String callBackId = update.getCallbackQuery().getId();
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery(callBackId);
        messageService.sendAnswerCallbackQuery(answerCallbackQuery);
        messageService.sendMessage(chatId, SUBSCRIBE_MESSAGE);
    }
}
