package com.github.chat.controllers.impl;

import com.github.chat.controllers.IMessageController;
import com.github.chat.entity.Message;
import com.github.chat.service.IMessageService;

import java.util.List;

public class MessageController implements IMessageController {

    private final IMessageService messageService;

    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public List<Message> findAll() {
        return this.messageService.findAll();
    }

    @Override
    public List<Message> findAllByRoom(Long roomId) {
        return this.messageService.findAllByRoom(roomId);
    }

}
