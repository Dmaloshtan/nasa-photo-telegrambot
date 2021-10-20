package ru.home.projects.nasaphototelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.command.annotation.AdminCommand;
import ru.home.projects.nasaphototelegrambot.repository.entity.TelegramUser;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;

import java.util.List;

@AdminCommand
public class StatCommand implements Command{

    private final TelegramUserService telegramUserService;
    private final SendBotMessageService sendBotMessageService;

    public StatCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.telegramUserService = telegramUserService;
        this.sendBotMessageService = sendBotMessageService;
    }

    public static final String STAT_MESSAGE = "NasaPhoto Telegram Bot использует %s человек:\n" +
            "Username           Статус подписки\n";

    @Override
    public void execute(Update update) {
        int activeUserCount = telegramUserService.retrieveAllActiveUsers().size();
        List<TelegramUser> users = telegramUserService.retrieveAllActiveUsers();
        StringBuilder builderMessage = new StringBuilder();

        for(TelegramUser user : users){
            builderMessage.append(user.getUsername()).append("           ").append(user.isSubscribe()).append("\n");
        }

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), String.format(STAT_MESSAGE, activeUserCount));
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), String.format(builderMessage.toString(), activeUserCount));
    }
}
