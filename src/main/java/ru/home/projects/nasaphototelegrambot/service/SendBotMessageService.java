package ru.home.projects.nasaphototelegrambot.service;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.List;

public interface SendBotMessageService {

    void sendMessage(String chatId, String message);

    void sendMessage(String chatId, String message, ReplyKeyboard keyboardMarkup);

    void sendAnswerCallbackQuery(AnswerCallbackQuery answerCallbackQuery);

    void sendPhotoGroup(String chatId, List<InputMedia> photos);
}
