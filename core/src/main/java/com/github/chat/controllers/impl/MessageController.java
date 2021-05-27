package com.github.chat.controllers.impl;

import com.github.chat.controllers.IMessageController;
import com.github.chat.service.IMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageController implements IMessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    private final IMessageService messageService;

    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void saveMessage(String nickname, String message) {

    }

    @Override
    public void updateMessage(String nickname, String message) {

    }
}
