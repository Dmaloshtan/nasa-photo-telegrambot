package ru.home.projects.nasaphototelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;

public class StopCommand implements Command {


    private final SendBotMessageService messageService;
    private final TelegramUserService telegramUserService;

    public StopCommand(SendBotMessageService messageService, TelegramUserService telegramUserService) {
        this.messageService = messageService;
        this.telegramUserService = telegramUserService;
    }

    public final static String STOP_MESSAGE = "Деактивировал все ваши подписки \\uD83D\\uDE1F.";

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
        telegramUserService.findByChatId(update.getMessage().getChatId().toString())
                .ifPresent(it -> {
                    it.setSubscribe(false);
                    telegramUserService.save(it);
                });
    }
}
