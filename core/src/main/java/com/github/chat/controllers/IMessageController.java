package com.github.chat.controllers;

import com.github.chat.entity.Message;

import java.util.List;

public interface IMessageController {

    List<Message> findAll();

    List<Message> findAllByRoom(Long roomId);
}
