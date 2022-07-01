package ru.home.projects.nasaphototelegrambot.command;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.utils.AnswerMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit-level testing for AstronomyPictureOfTheDayCommand")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AstronomyPictureOfTheDayCommandTest{

    @InjectMocks
    private AstronomyPictureOfTheDayCommand astronomyPictureOfTheDayCommand;

    @Mock
    private SendBotMessageService messageService;

    private final Long CHAT_ID = 1234567824356L;
    private Update update;

    @BeforeAll
    void init() {
        Chat chat = new Chat();
        chat.setId(CHAT_ID);

        Message message = new Message();
        message.setChat(chat);
        message.setText(getCommandName());

        update = new Update();
        update.setMessage(message);
    }

    @Test
    public void execute_shouldProperlyExecuteCommand() {
        //ARRANGE
        ArgumentCaptor<String> chatIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<ReplyKeyboard> replyKeyboardCaptor = ArgumentCaptor.forClass(ReplyKeyboard.class);

        doNothing().when(messageService).sendMessage(chatIdCaptor.capture(), messageCaptor.capture(), replyKeyboardCaptor.capture());

        //ACT
        astronomyPictureOfTheDayCommand.execute(update);

        //VERIFY
        Assertions.assertThat(chatIdCaptor.getValue()).isEqualTo(CHAT_ID.toString());
        Assertions.assertThat(messageCaptor.getValue()).isEqualTo(getCommandMessage());
        Assertions.assertThat(replyKeyboardCaptor.getValue()).isEqualTo(getReplyKeyboard());
    }

    String getCommandName() {
        return CommandName.APOD.getCommandName();
    }

    String getCommandMessage() {
        return AnswerMessage.ASTRONOMY_PICTURE_OF_THE_DAY_COMMAND;
    }

    ReplyKeyboard getReplyKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("Фото сегодняшнего дня")
                                .callbackData("today")
                                .build(),
                        InlineKeyboardButton.builder()
                                .text("Выбрать дату")
                                .callbackData("DatePhoto")
                                .build())
        );
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }
}