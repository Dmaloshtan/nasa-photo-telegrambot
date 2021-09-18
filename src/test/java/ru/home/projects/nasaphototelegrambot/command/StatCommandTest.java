package ru.home.projects.nasaphototelegrambot.command;

public class StatCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return CommandName.STAT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return String.format(StatCommand.STAT_MESSAGE, 0);
    }

    @Override
    Command getCommand() {
        return new StatCommand(messageService, telegramUserService);
    }
}
