package com.github.chat.repository;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;

import java.util.List;

public interface IUsersRepo {

    User insert(UserRegDto userRegDto);

    User findById(long id);

    User findByAuth(UserAuthDto userAuthDto);

    void update(UserRegDto userRegDto);

    void delete(UserRegDto userRegDto);

    //User save(User user);

    //List<User> findAll();
}
