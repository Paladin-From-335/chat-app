package com.github.chat.service.impl;

import com.github.chat.entity.Message;
import com.github.chat.repository.IRepository;
import com.github.chat.service.IMessageService;
import com.github.chat.utils.HibernateUtils;

import java.util.Collection;

public class MessageService implements IMessageService {

    private final IRepository<Message> iRepository;

    public MessageService(IRepository<Message> iRepository) {
        this.iRepository = iRepository;
    }

    @Override
    public Collection<Message> findAllByRoom(Long room_id) {
        return this.iRepository.findAllBy("room_id", room_id, HibernateUtils.getSession());
    }

    @Override
    public void insert(Message message) {
        this.iRepository.save(message, HibernateUtils.getSession());
    }

    @Override
    public void update(Message message) {
        this.iRepository.update(message, HibernateUtils.getSession());
    }
}
