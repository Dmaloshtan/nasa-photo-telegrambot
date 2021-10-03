package ru.home.projects.nasaphototelegrambot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.command.CommandContainer;
import ru.home.projects.nasaphototelegrambot.command.CommandName;
import ru.home.projects.nasaphototelegrambot.handleCallback.CallBackContainer;
import ru.home.projects.nasaphototelegrambot.handleCallback.PictureOfTheDayCallback;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClientImpl;
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
    private final CallBackContainer callBackContainer;

    @Autowired
    public NasaPhotoTelegramBot(TelegramUserService telegramUserService, NasaClientImpl nasaClient) {
        commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), telegramUserService);
        callBackContainer = new CallBackContainer(new SendBotMessageServiceImpl(this), telegramUserService, nasaClient);
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasCallbackQuery()){
            System.out.println(update.getCallbackQuery().getData());
            callBackContainer.retrieveCommand(update.getCallbackQuery().getData()).execute(update);
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = setPrefixToMessage(update);

            if (message.startsWith(COMMAND_PREFIX)) {
                commandContainer.retrieveCommand(message).execute(update);
            } else {
                commandContainer.retrieveCommand(CommandName.NO.getCommandName()).execute(update);
            }
        }
    }

    private String setPrefixToMessage(Update update){
        String message = update.getMessage().getText().trim();
        if(!message.startsWith("/")){
            message = "/" + message;
        }
        return message;
    }

}
