package com.github.chat.config;

import com.github.chat.controllers.IUsersController;
import com.github.chat.controllers.UsersController;

public class ControllerConfig {

    private static final IUsersController userController = new UsersController(
            ServiceConfig.getUserService()
    );

    public static IUsersController getUserController() {
        return userController;
    }
}