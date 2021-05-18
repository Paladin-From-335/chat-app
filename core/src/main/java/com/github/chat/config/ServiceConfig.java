package com.github.chat.config;

import com.github.chat.service.IUsersService;
import com.github.chat.service.impl.UserService;

public class ServiceConfig {

    private static final IUsersService userService = new UserService(RepositoryConfig.getUsersRepository());

    public static IUsersService getUserService() {
        return userService;
    }
}
