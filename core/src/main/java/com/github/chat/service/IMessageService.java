package com.github.chat.service;

import com.github.chat.entity.Message;

import java.util.List;

public interface IMessageService {

    List<Message> findAll();

    List<Message> findAllByRoom(Long roomId);

    void save(String login, String message);
}
