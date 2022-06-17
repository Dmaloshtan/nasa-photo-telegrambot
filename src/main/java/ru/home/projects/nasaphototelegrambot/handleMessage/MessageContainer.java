package ru.home.projects.nasaphototelegrambot.handleMessage;

import com.google.common.collect.ImmutableMap;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClientImpl;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;

public class MessageContainer {

    private final ImmutableMap<String, ResponseMessage> messageMap;

    public MessageContainer(SendBotMessageService messageService, TelegramUserService userService, NasaClientImpl nasaClient) {
        messageMap = ImmutableMap.<String, ResponseMessage>builder().
                put(MessageName.DATE.getCallbackName(), new AstronomyPictureOfTheDifferentDay(messageService, nasaClient, userService))
                .build();
    }

    public ResponseMessage retrieveCommand(String messageIdentifier) {
        return messageMap.get(messageIdentifier);
    }
}
