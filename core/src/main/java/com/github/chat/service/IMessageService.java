package com.github.chat.service;

import com.github.chat.entity.Message;

import java.util.Collection;

public interface IMessageService {

    Collection<Message> findAllByRoom(Long room_id);

    void insert(Message message);

    void update(Message message);

}
