package ru.home.projects.nasaphototelegrambot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public interface SendBotMessageService {

    void sendMessage(String chatId, String message);
    void sendMessage(String chatId, String message, ReplyKeyboardMarkup keyboardMarkup);
}
