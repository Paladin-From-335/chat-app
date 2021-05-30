package com.github.chat.repository;

import com.github.chat.entity.Message;

import java.util.Collection;
import java.util.List;

public interface IMessageRepository extends Repository<Message, Long>{

    List<Message> findAllByRoom(Long roomId);
}
