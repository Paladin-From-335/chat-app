package com.github.chat.config;

import com.github.chat.controllers.IMessageController;
import com.github.chat.controllers.IUsersController;
import com.github.chat.controllers.impl.MessageController;
import com.github.chat.controllers.impl.UsersController;

public class ControllerConfig {

    private static final IUsersController userController = new UsersController(
            ServiceConfig.getUserService()
    );


    private static final IMessageController messageController = new MessageController(
            ServiceConfig.getMessageService()
    );

    public static IUsersController getUserController() {
        return userController;
    }

    public static IMessageController getMessageController() {
        return messageController;
    }
}