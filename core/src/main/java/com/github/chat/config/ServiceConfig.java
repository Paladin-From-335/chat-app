package com.github.chat.config;

import com.github.chat.service.IMessageService;
import com.github.chat.service.IUsersService;
import com.github.chat.service.impl.MessageService;
import com.github.chat.service.impl.UserService;

public class ServiceConfig {

    private static final IUsersService userService = new UserService(RepositoryConfig.getUsersRepository());



    private static final IMessageService messageService = new MessageService(RepositoryConfig.getMessageRepository());

    public static IUsersService getUserService() {
        return userService;
    }

    public static IMessageService getMessageService() {
        return messageService;
    }
}
