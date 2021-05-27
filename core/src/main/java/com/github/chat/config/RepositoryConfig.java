package com.github.chat.config;

import com.github.chat.entity.Message;
import com.github.chat.entity.User;
import com.github.chat.repository.IRepository;
import com.github.chat.repository.impl.Repository;

public class RepositoryConfig {

    private static final IRepository<User> usersRepository = new Repository<>(User.class);

    private static final IRepository<Message> messageRepository = new Repository<>(Message.class);

    public static IRepository<Message> getMessageRepository() {
        return messageRepository;
    }

    public static IRepository<User> getUsersRepository() {
        return usersRepository;
    }
}
