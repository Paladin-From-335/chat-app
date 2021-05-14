package com.github.chat.repository;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;

import java.util.Collection;

public interface IUsersRepo<T> {

    Collection<T> findAll();

    User findByAuth(UserAuthDto userAuthorizationDto);

    User findByReg(UserRegDto userRegistrationDto);

    User insert(UserRegDto userRegistrationDto);

    User update(User user);

    void delete(User user);

    void deleteAll();

}
