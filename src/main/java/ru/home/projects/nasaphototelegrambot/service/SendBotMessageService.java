package ru.home.projects.nasaphototelegrambot.service;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public interface SendBotMessageService {

    void sendMessage(String chatId, String message);
    void sendMessage(String chatId, String message, ReplyKeyboard keyboardMarkup);
    void sendAnswerCallbackQuery(AnswerCallbackQuery answerCallbackQuery);
}
