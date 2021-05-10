package com.github.chat.service;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;

public interface IUsersService {

    User create(UserRegDto userRegDto);

    User findById(long id);

    User findByAuth(UserAuthDto userAuthDto);

    void update(UserRegDto userRegDto);

    void delete(UserRegDto userRegDto);
}
