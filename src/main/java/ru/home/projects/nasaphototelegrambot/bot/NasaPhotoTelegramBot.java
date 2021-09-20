package ru.home.projects.nasaphototelegrambot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.home.projects.nasaphototelegrambot.command.CommandContainer;
import ru.home.projects.nasaphototelegrambot.command.CommandName;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClient;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageServiceImpl;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;

@Component
public class NasaPhotoTelegramBot extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";

    @Value("${bot.name}")
    private String username;
    @Value("${bot.token}")
    private String token;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    private final CommandContainer commandContainer;

    @Autowired
    public NasaPhotoTelegramBot(TelegramUserService telegramUserService, NasaClient nasaClient) {
        commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), telegramUserService, nasaClient);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(CommandName.NO.getCommandName()).execute(update);
            }
        }
    }
}
