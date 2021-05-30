package com.github.chat.controllers;

import com.github.chat.dto.ForgotDto;
import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;

import java.io.IOException;

public interface IUsersController {

    String authorize(UserAuthDto userAuthDto);

    void registration(UserRegDto userRegDto) throws IOException;

    String forgotReq(ForgotDto forgotDto) throws IOException;

    void updatePassword(ForgotDto forgotDto);

}
