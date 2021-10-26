package ru.home.projects.nasaphototelegrambot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;

public class HelpCommand implements Command {

    private final SendBotMessageService messageService;

    public HelpCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    public final static String HELP_MESSAGE = String.format("\uD83E\uDE90<b>Доступные команды</b>\uD83E\uDE90\n\n"
                    + "%s - \uD83C\uDF0E Nasa каждый день публикует \"Фото дня\". " +
                    "Туда попадают фотографии с космических телескопов, вроде Хаббл, фото астрономов-любителей" +
                    " или другие фото как-либо связанные с космосом. " +
                    "Нажав на кнопку вы сможете получить \"Фото дня\".\n\n"
                    + "%s - ☄️ Данная команда позволяет получить фотографии с марсоходов." +
                    " Всего существует 3 марсохода, 2 из которых на данный момент уже не используются," +
                    " но есть возможность получить фотографии с тех дней, когда они ещё ездили по поверхности Марса. " +
                    "Марсоход Curiosity находится в режиме работы и по сей день.\n" +
                    "Данные марсоходы делают десятки и сотни фотографий за один марсианский день. " +
                    "Это могут быть как красивые снимки рельефа Марса, так и случайные искажения" +
                    " в камере. При нажатии на кнопку вы получите сразу серию из " +
                    "10 случайных фотографий, сделанных в один день с одного выбранного марсохода.\n\n"
                    + "%s - \uD83D\uDCE1 подписаться/отписаться на ежедневную рассылку \"Фото дня\" в 14:00 по мск.\n\n"
                    + "%s - ⚒ Помощь по командам и кнопкам.\n\n",
            CommandName.APOD.getCommandName(), CommandName.MARS.getCommandName(),
            CommandName.SUBSCRIBE.getCommandName(), CommandName.HELP.getCommandName()
    );

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
