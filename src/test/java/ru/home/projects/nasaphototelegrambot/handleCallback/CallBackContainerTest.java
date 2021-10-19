package ru.home.projects.nasaphototelegrambot.handleCallback;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.home.projects.nasaphototelegrambot.command.Command;
import ru.home.projects.nasaphototelegrambot.command.CommandContainer;
import ru.home.projects.nasaphototelegrambot.command.CommandName;
import ru.home.projects.nasaphototelegrambot.command.UnknownCommand;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClient;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-level testing for CallBackContainer")
class CallBackContainerTest {

    private CallBackContainer callBackContainer;

    @BeforeEach
    public void init(){
        SendBotMessageService messageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        NasaClient nasaClient = Mockito.mock(NasaClient.class);
        callBackContainer = new CallBackContainer(messageService, telegramUserService, nasaClient);
    }

    @Test
    public void shouldGetAllTheExistingCommands(){
        //when-then
        Arrays.stream(CallbackName.values())
                .forEach(commandName -> {
                    ResponseCallbackQuery callbackQuery = callBackContainer.retrieveCommand(commandName.getCallbackName());
                    Assertions.assertNotEquals(null, callbackQuery.getClass());
                });
    }


}