package ru.home.projects.nasaphototelegrambot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.command.annotation.AdminCommand;
import ru.home.projects.nasaphototelegrambot.repository.entity.TelegramUser;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;
import ru.home.projects.nasaphototelegrambot.utils.AnswerMessage;

import java.util.List;

@AdminCommand
public class StatCommand implements Command {

    private final TelegramUserService telegramUserService;
    private final SendBotMessageService sendBotMessageService;

    public StatCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.telegramUserService = telegramUserService;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        List<TelegramUser> users = telegramUserService.retrieveAllActiveUsers();
        int activeUserCount = users.size();
        String userStatistics = getStatisticsMessage(users);

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), String.format(AnswerMessage.STAT_COMMAND, activeUserCount, userStatistics));
    }

    private String getStatisticsMessage(List<TelegramUser> users) {
        StringBuilder builderMessage = new StringBuilder();

        for (TelegramUser user : users) {
            builderMessage.append(user.getUsername()).append("           ").append(user.isSubscribe()).append("\n");
        }
        return builderMessage.toString();
    }
}
