package com.github.chat.repository;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;

import java.util.List;

public interface IUsersRepo {

    User insert(UserRegDto userRegDto);

    User findByReg(UserRegDto userRegDto);

    User findByAuth(UserAuthDto userAuthDto);

    void update(User user);

    void delete(User user);

    List<User> findAll();

}
