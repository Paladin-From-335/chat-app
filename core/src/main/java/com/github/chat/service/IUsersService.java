package com.github.chat.service;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;

public interface IUsersService {

    User findById(long id);

    User findByAuth(UserAuthDto authDto);

    User insert(UserRegDto regDto);

    void delete(UserRegDto regDto);

    void update(UserRegDto regDto);
}

