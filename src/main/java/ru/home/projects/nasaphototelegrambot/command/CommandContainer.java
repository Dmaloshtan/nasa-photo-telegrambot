package ru.home.projects.nasaphototelegrambot.command;

import com.google.common.collect.ImmutableMap;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClientImpl;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;

public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final UnknownCommand unknownCommand;

    public CommandContainer(SendBotMessageService messageService, TelegramUserService userService, NasaClientImpl nasaClient) {
        commandMap = ImmutableMap.<String, Command>builder().
                put(CommandName.START.getCommandName(), new StartCommand(messageService, userService)).
                put(CommandName.STOP.getCommandName(), new StopCommand(messageService, userService)).
                put(CommandName.HELP.getCommandName(), new HelpCommand(messageService)).
                put(CommandName.NO.getCommandName(), new NoCommand(messageService)).
                put(CommandName.STAT.getCommandName(), new StatCommand(messageService, userService)).
                put(CommandName.APOD.getCommandName(), new AstronomyPictureOfTheDayCommand(messageService, nasaClient))
                .build();

        unknownCommand = new UnknownCommand(messageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
