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
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.home.projects.nasaphototelegrambot.repository.entity.TelegramUser;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;
import ru.home.projects.nasaphototelegrambot.utils.AnswerMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit-level testing for StartCommand")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StartCommandTest {

    @InjectMocks
    private StartCommand startCommand;

    @Mock
    private SendBotMessageService messageService;
    @Mock
    private  TelegramUserService telegramUserService;

    private final Long CHAT_ID = 1234567824356L;
    private TelegramUser telegramUser;
    private Update update;

    @BeforeAll
    void init() {
        Chat chat = new Chat();
        chat.setId(CHAT_ID);

        String username = "User";

        User user = new User();

        user.setUserName(username);

        telegramUser = new TelegramUser();
        telegramUser.setChatId(CHAT_ID.toString());
        telegramUser.setUsername(username);

        Message message = new Message();
        message.setChat(chat);
        message.setText(getCommandName());
        message.setFrom(user);

        update = new Update();
        update.setMessage(message);
    }

    @Test
    public void execute_shouldProperlyExecuteCommand() {
        //ARRANGE
        ArgumentCaptor<String> chatIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<ReplyKeyboard> replyKeyboardCaptor = ArgumentCaptor.forClass(ReplyKeyboard.class);

        when(telegramUserService.findByChatId(any(String.class))).thenReturn(Optional.of(telegramUser));
        doNothing().when(messageService).sendMessage(chatIdCaptor.capture(), messageCaptor.capture(), replyKeyboardCaptor.capture());

        //ACT
        startCommand.execute(update);

        //VERIFY
        Assertions.assertThat(chatIdCaptor.getValue()).isEqualTo(CHAT_ID.toString());
        Assertions.assertThat(messageCaptor.getValue()).isEqualTo(getCommandMessage());
        Assertions.assertThat(replyKeyboardCaptor.getValue()).isEqualTo(getReplyKeyboard());
    }

    private String getCommandName() {
        return CommandName.START.getCommandName();
    }

    private String getCommandMessage() {
        return AnswerMessage.START_COMMAND;
    }

    private ReplyKeyboard getReplyKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("photo");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("mars");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("subscribe");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("help");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }
}
