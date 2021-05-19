package com.github.chat.service;

import com.github.chat.entity.User;

import java.util.Collection;

public interface IUsersService {

    Collection<User> findAll();

    User findById(Long id);

    User findByLogin(String login);

    User findByEmail(String email);

    void insert(User user);

    void update(User user);
}

