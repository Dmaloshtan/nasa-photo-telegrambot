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
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.utils.AnswerMessage;

import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit-level testing for UnknownCommand")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UnknownCommandTest {

    @InjectMocks
    private UnknownCommand unknownCommand;

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

        doNothing().when(messageService).sendMessage(chatIdCaptor.capture(), messageCaptor.capture());

        //ACT
        unknownCommand.execute(update);

        //VERIFY
        Assertions.assertThat(chatIdCaptor.getValue()).isEqualTo(CHAT_ID.toString());
        Assertions.assertThat(messageCaptor.getValue()).isEqualTo(getCommandMessage());
    }

    String getCommandName() {
        return CommandName.HELP.getCommandName();
    }
    // TODO здесь так же протестировать на различные варианты команд

    String getCommandMessage() {
        return AnswerMessage.UNKNOWN_COMMAND;
    }
}
