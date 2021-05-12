package com.github.chat.config;

import com.github.chat.controllers.UsersController;
import com.github.chat.repository.IUsersRepo;
import com.github.chat.repository.impl.UserRepo;
import com.github.chat.service.IUsersService;
import com.github.chat.service.impl.UserService;

public class ControllerConfig {

    public static UsersController usersController(){
        return new UsersController(DBConfig.getUsersService());
    }
    }