package com.github.chat.controllers;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.exceptions.UserAlreadyExistException;
import com.github.chat.payload.Token;
import com.github.chat.service.impl.UserService;
import com.github.chat.utils.TokenProvider;

public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    public String auth(UserAuthDto payload) {
        User user = this.userService.findByAuth(payload);
        return TokenProvider.encode(new Token(user));
    }

    public void registration(UserRegDto userRegistrationDto) {
        if (this.userService.findByAuth(new UserAuthDto(userRegistrationDto)) != null) {
            throw new UserAlreadyExistException();
        }
        userService.insert(userRegistrationDto);
    }
}
