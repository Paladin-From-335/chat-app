package com.github.chat.repository;

import com.github.chat.entity.User;

import java.util.List;

public interface IUserRepository extends Repository<User, Long> {

    User findOneByLogin(String login);

    User findOneByEmail(String email);
}
