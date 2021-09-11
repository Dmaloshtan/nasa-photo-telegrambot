package ru.home.projects.nasaphototelegrambot.service;

public interface SendBotMessageService {

    void sendMessage(String chatId, String message);
}
