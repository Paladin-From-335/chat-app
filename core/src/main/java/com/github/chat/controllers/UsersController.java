package com.github.chat.controllers;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.payload.Token;
import com.github.chat.service.IUsersService;
import com.github.chat.utils.TokenProvider;

import java.util.Date;

public class UsersController {

    private final IUsersService usersService;

    public UsersController(IUsersService usersService) {
        this.usersService = usersService;
    }

    public String auth(UserAuthDto userAuthDto) {
        User user = this.usersService.findByAuth(userAuthDto);
        return TokenProvider.encode(new Token(1L, "First Name", "Last Name", new Date(), new Date()));
    }

    public void reg(UserRegDto userRegDto) {
        if (this.usersService.findByAuth(new UserAuthDto(userRegDto)) == null) {
            this.usersService.create(userRegDto);
        }
    }
}
