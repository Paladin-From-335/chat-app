package com.github.chat.config;

import com.github.chat.entity.User;
import com.github.chat.repository.IUsersRepo;
import com.github.chat.repository.impl.UserRepo;

public class RepositoryConfig {

    private static final IUsersRepo<User> usersRepository = new UserRepo<>(User.class);

    public static IUsersRepo<User> getUsersRepository() {
        return usersRepository;
    }
}
