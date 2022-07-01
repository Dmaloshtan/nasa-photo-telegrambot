package ru.home.projects.nasaphototelegrambot.handleCallback;

import com.google.common.collect.ImmutableMap;
import ru.home.projects.nasaphototelegrambot.nasaClient.NasaClient;
import ru.home.projects.nasaphototelegrambot.service.SendBotMessageService;
import ru.home.projects.nasaphototelegrambot.service.TelegramUserService;

public class CallBackContainer {

    private final ImmutableMap<String, ResponseCallbackQuery> callbackMap;

    public CallBackContainer(SendBotMessageService messageService, TelegramUserService userService, NasaClient nasaClient) {
        callbackMap = ImmutableMap.<String, ResponseCallbackQuery>builder().
                put(CallbackName.APODTODAY.getCallbackName(), new PictureOfTheDayCallback(messageService, nasaClient))
                .put(CallbackName.APODDIF.getCallbackName(), new PictureOfTheDayDifferentCallback(messageService, userService))
                .put(CallbackName.SUBSCRIBE.getCallbackName(), new SubscribeCallback(messageService, userService))
                .put(CallbackName.UNSUBSCRIBE.getCallbackName(), new UnsubscribeCallback(messageService, userService))
                .put(CallbackName.CURIOSITY_ROVER.getCallbackName(), new MarsPhotoCallback(messageService, nasaClient))
                .put(CallbackName.OPPORTUNITY_ROVER.getCallbackName(), new MarsPhotoCallback(messageService, nasaClient))
                .put(CallbackName.SPIRIT_ROVER.getCallbackName(), new MarsPhotoCallback(messageService, nasaClient))
                .build();
    }

    public ResponseCallbackQuery retrieveCommand(String callbackIdentifier) {
        return callbackMap.get(callbackIdentifier);
    }
}
