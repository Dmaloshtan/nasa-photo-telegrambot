package ru.home.projects.nasaphototelegrambot.handleCallback;

import com.google.common.collect.ImmutableMap;
import ru.home.projects.nasaphototelegrambot.command.*;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClientImpl;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;

public class CallBackContainer {

    private final ImmutableMap<String, ResponseCallbackQuery> callbackMap;

    public CallBackContainer(SendBotMessageService messageService, TelegramUserService userService, NasaClientImpl nasaClient) {
        callbackMap = ImmutableMap.<String, ResponseCallbackQuery>builder().
                put(CallbackName.APODTODAY.getCallbackName(), new PictureOfTheDayCallback(messageService, nasaClient))
                .put(CallbackName.APODDIF.getCallbackName(), new PictureOfTheDayDifferentCallback(messageService, userService))
                .put(CallbackName.SUBSCRIBE.getCallbackName(), new SubscribeCallback(messageService,userService))
                .put(CallbackName.UNSUBSCRIBE.getCallbackName(), new UnsubscribeCallback(messageService, userService))
                .build();
    }

    public ResponseCallbackQuery retrieveCommand(String callbackIdentifier) {
        return callbackMap.get(callbackIdentifier);
    }

}
