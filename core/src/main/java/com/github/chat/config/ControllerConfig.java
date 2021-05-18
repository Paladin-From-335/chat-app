package com.github.chat.config;

import com.github.chat.controllers.IUsersController;
import com.github.chat.controllers.UsersController;
import com.github.chat.service.impl.UserService;

public class ControllerConfig {

    private static final IUsersController userController = new UsersController(
            (UserService) ServiceConfig.getUserService()
    );

    public static IUsersController getUserController() {
        return userController;
    }
}