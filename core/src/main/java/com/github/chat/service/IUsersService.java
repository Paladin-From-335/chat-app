package com.github.chat.service;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;

import java.util.List;

public interface IUsersService {

    User create(UserRegDto userRegDto);

    List<User> findAll();

    User findByReg(UserRegDto userRegDto);

    User findByAuth(UserAuthDto userAuthDto);

    User insert(UserRegDto userRegDto);

    void update(User user);

    void delete(User user);
}
