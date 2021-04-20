package com.github.chat.config;

import com.github.chat.handlers.UsersHandler;

public class HandlerConfig {

    public static UsersHandler usersController(){
        return new UsersHandler(ControllerConfig.usersController());
    }
}
