package ru.home.projects.nasaphototelegrambot.command;

import com.google.common.collect.ImmutableMap;
import ru.home.projects.nasaphototelegrambot.command.annotation.AdminCommand;
import ru.home.projects.nasaphototelegrambot.handleCallback.PictureOfTheDayCallback;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClientImpl;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;

import java.util.List;

import static java.util.Objects.nonNull;

public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final UnknownCommand unknownCommand;
    private final List<String> admins;

    public CommandContainer(SendBotMessageService messageService, TelegramUserService userService, List<String> admins) {
        commandMap = ImmutableMap.<String, Command>builder().
                put(CommandName.START.getCommandName(), new StartCommand(messageService, userService)).
                put(CommandName.STOP.getCommandName(), new StopCommand(messageService, userService)).
                put(CommandName.HELP.getCommandName(), new HelpCommand(messageService)).
                put(CommandName.NO.getCommandName(), new NoCommand(messageService)).
                put(CommandName.STAT.getCommandName(), new StatCommand(messageService, userService)).
                put(CommandName.APOD.getCommandName(), new AstronomyPictureOfTheDayCommand(messageService)).
                put(CommandName.SUBSCRIBE.getCommandName(), new SubscribeCommand(messageService)).
                put(CommandName.MARS.getCommandName(), new MarsCommand(messageService))
                .build();
        this.admins = admins;
        unknownCommand = new UnknownCommand(messageService);
    }

    public Command retrieveCommand(String commandIdentifier, String username) {
        Command orDefault = commandMap.getOrDefault(commandIdentifier, unknownCommand);
        if (isAdminCommand(orDefault)) {
            if (admins.contains(username)) {
                return orDefault;
            } else {
                return unknownCommand;
            }
        }
        return orDefault;
    }

    private boolean isAdminCommand(Command command) {
        return nonNull(command.getClass().getAnnotation(AdminCommand.class));
    }

}
