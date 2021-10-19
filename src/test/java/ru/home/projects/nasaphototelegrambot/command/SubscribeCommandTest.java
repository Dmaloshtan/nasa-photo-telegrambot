package ru.home.projects.nasaphototelegrambot.command;

import org.junit.jupiter.api.DisplayName;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DisplayName("Unit-level testing for SubscribeCommand")
public class SubscribeCommandTest extends AbstractCommandTest{


    @Override
    String getCommandName() {
        return CommandName.SUBSCRIBE.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return SubscribeCommand.SUBSCRIBE_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new SubscribeCommand(messageService);
    }

    @Override
    ReplyKeyboard getReplyKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("Подписаться")
                                .callbackData("subscribe")
                                .build(),
                        InlineKeyboardButton.builder()
                                .text("Отменить подписку")
                                .callbackData("unsubscribe")
                                .build())
        );
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }
}
