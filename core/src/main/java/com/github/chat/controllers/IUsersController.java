package com.github.chat.controllers;

import com.github.chat.dto.ForgotDto;
import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;

import java.io.IOException;

public interface IUsersController {

    String authorize(UserAuthDto userAuthDto);

    void registration(UserRegDto userRegDto) throws IOException;

    String forgotReq(ForgotDto forgotDto) throws IOException;

    void verificationEmail(UserRegDto userRegDto);

}
