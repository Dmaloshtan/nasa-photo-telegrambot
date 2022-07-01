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

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit-level testing for StatCommand")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StatCommandTest {

    @InjectMocks
    private StatCommand statCommand;

    @Mock
    private SendBotMessageService messageService;
    @Mock
    private TelegramUserService telegramUserService;

    private final Long CHAT_ID = 1234567824356L;
    private Update update;
    private List<TelegramUser> users;

    @BeforeAll
    void init() {
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setChatId(CHAT_ID.toString());
        telegramUser.setUsername("User");
        Chat chat = new Chat();
        chat.setId(CHAT_ID);

        Message message = new Message();
        message.setChat(chat);
        message.setText(getCommandName());

        update = new Update();
        update.setMessage(message);

        users = List.of(telegramUser);
    }

    @Test
    public void execute_shouldProperlyExecuteCommand() {
        //ARRANGE
        ArgumentCaptor<String> chatIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> answerMessageCaptor = ArgumentCaptor.forClass(String.class);

        when(telegramUserService.retrieveAllActiveUsers()).thenReturn(users);
        doNothing().when(messageService).sendMessage(chatIdCaptor.capture(), answerMessageCaptor.capture());

        //ACT
        statCommand.execute(update);

        //VERIFY
        Assertions.assertThat(chatIdCaptor.getValue()).isEqualTo(CHAT_ID.toString());
        Assertions.assertThat(answerMessageCaptor.getValue()).isEqualTo(getCommandMessage());
    }

    private String getCommandName() {
        return CommandName.STAT.getCommandName();
    }

    private String getCommandMessage() {
        return String.format(AnswerMessage.STAT_COMMAND, users.size(), getStatisticsMessage(users));
    }

    private String getStatisticsMessage(List<TelegramUser> users) {
        StringBuilder builderMessage = new StringBuilder();

        for (TelegramUser user : users) {
            builderMessage.append(user.getUsername()).append("           ").append(user.isSubscribe()).append("\n");
        }
        return builderMessage.toString();
    }
}
