package com.github.chat.service.impl;

import com.github.chat.entity.Message;
import com.github.chat.payload.PrivateToken;
import com.github.chat.repository.IMessageRepository;
import com.github.chat.repository.impl.MessageRepository;
import com.github.chat.service.IMessageService;
import com.github.chat.utils.PrivateTokenProvider;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class MessageService implements IMessageService {

    private final IMessageRepository messageRepository;

    private PrivateToken privateToken;

    public MessageService(IMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> findAllByRoom(Long roomId) {
        return messageRepository.findAllByRoom(roomId);
    }

    @Override
    public void save(String nickname, String message) {
        Message entityToSave = new Message(100L, message, nickname, Timestamp.from(Instant.now()));
        messageRepository.save(entityToSave);
    }
}
