package ru.home.projects.nasaphototelegrambot.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClient;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageServiceImpl;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-level testing for CommandContainer")
class CommandContainerTest {

    private CommandContainer commandContainer;

    @BeforeEach
    public void init(){
        SendBotMessageService messageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        NasaClient nasaClient = Mockito.mock(NasaClient.class);
        commandContainer = new CommandContainer(messageService, telegramUserService, nasaClient);
    }

    @Test
    public void shouldGetAllTheExistingCommands(){
        //when-then
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = commandContainer.retrieveCommand(commandName.getCommandName());
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    public void shouldReturnUnknownCommand(){
        String unknownCommand = "qweqweqwe";

        Command command = commandContainer.retrieveCommand(unknownCommand);

        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }

}

