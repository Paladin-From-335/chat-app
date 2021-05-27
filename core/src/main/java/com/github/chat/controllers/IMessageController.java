package com.github.chat.controllers;

public interface IMessageController {

    void saveMessage(String nickname, String message);

    void updateMessage(String nickname, String message);
}
