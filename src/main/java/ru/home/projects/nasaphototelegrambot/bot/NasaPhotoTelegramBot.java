package ru.home.projects.nasaphototelegrambot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.command.CommandContainer;
import ru.home.projects.nasaphototelegrambot.command.CommandName;
import ru.home.projects.nasaphototelegrambot.handleCallback.CallBackContainer;
import ru.home.projects.nasaphototelegrambot.handleMessage.MessageContainer;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClientImpl;
import ru.home.projects.nasaphototelegrambot.repository.entity.TelegramUser;
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
    private final MessageContainer messageContainer;
    private final TelegramUserService telegramUserService;

    @Autowired
    public NasaPhotoTelegramBot(TelegramUserService telegramUserService, NasaClientImpl nasaClient) {
        commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), telegramUserService);
        callBackContainer = new CallBackContainer(new SendBotMessageServiceImpl(this), telegramUserService, nasaClient);
        messageContainer = new MessageContainer(new SendBotMessageServiceImpl(this), telegramUserService, nasaClient);
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasCallbackQuery()) {
            callBackContainer.retrieveCommand(update.getCallbackQuery().getData()).execute(update);
        }

        if (update.hasMessage() && update.getMessage().hasText()) {

            String message = setPrefixToMessage(update);
            TelegramUser telegramUser = telegramUserService.findByChatId(update.getMessage().getChatId().toString()).get();

            if (message.startsWith(COMMAND_PREFIX)) {
                commandContainer.retrieveCommand(message).execute(update);
                telegramUser.setBotState("default");
            } else if (telegramUser.getBotState().equals(BotState.SET_DATE.getBotState())) {
                messageContainer.retrieveCommand("date").execute(update);
            } else {
                commandContainer.retrieveCommand(CommandName.NO.getCommandName()).execute(update);
            }
        }
    }

    private String setPrefixToMessage(Update update) {
        String message = update.getMessage().getText().trim();

        if (!message.startsWith("/")) {
            for(CommandName name: CommandName.values()){
                if(name.getCommandName().substring(1).equals(message)){
                    message = "/" + message;
                }
            }

        }
        return message;
    }

}
