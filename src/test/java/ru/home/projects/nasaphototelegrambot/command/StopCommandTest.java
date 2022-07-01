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
import ru.home.projects.nasaphototelegrambot.repository.entity.TelegramUser;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;
import ru.home.projects.nasaphototelegrambot.utils.AnswerMessage;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit-level testing for StopCommand")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StopCommandTest {

    @InjectMocks
    private StopCommand stopCommand;

    @Mock
    private SendBotMessageService messageService;
    @Mock
    private TelegramUserService telegramUserService;

    private final Long CHAT_ID = 1234567824356L;
    private Update update;
    private TelegramUser telegramUser;

    @BeforeAll
    void init() {
        Chat chat = new Chat();
        chat.setId(CHAT_ID);

        Message message = new Message();
        message.setChat(chat);
        message.setText(getCommandName());

        telegramUser = new TelegramUser();
        telegramUser.setChatId(CHAT_ID.toString());
        telegramUser.setUsername("User");

        update = new Update();
        update.setMessage(message);
    }

    @Test
    public void execute_shouldProperlyExecuteCommand() {
        //ARRANGE
        ArgumentCaptor<String> chatIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

        when(telegramUserService.findByChatId(any(String.class))).thenReturn(Optional.of(telegramUser));
        doNothing().when(messageService).sendMessage(chatIdCaptor.capture(), messageCaptor.capture());

        //ACT
        stopCommand.execute(update);

        //VERIFY
        Assertions.assertThat(chatIdCaptor.getValue()).isEqualTo(CHAT_ID.toString());
        Assertions.assertThat(messageCaptor.getValue()).isEqualTo(getCommandMessage());
    }

    String getCommandName() {
        return CommandName.STOP.getCommandName();
    }

    String getCommandMessage() {
        return AnswerMessage.STOP_COMMAND;
    }
}
