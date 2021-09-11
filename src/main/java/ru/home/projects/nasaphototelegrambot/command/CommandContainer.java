package ru.home.projects.nasaphototelegrambot.command;

import com.google.common.collect.ImmutableMap;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final UnknownCommand unknownCommand;

    public CommandContainer(SendBotMessageService messageService) {
        commandMap = ImmutableMap.<String, Command>builder().
                put(CommandName.START.getCommandName(), new StartCommand(messageService)).
                put(CommandName.STOP.getCommandName(), new StopCommand(messageService)).
                put(CommandName.HELP.getCommandName(), new HelpCommand(messageService)).
                put(CommandName.NO.getCommandName(), new NoCommand(messageService))
                .build();

        unknownCommand = new UnknownCommand(messageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

}
