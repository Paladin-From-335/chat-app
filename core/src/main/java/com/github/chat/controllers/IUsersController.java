package com.github.chat.controllers;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;

public interface IUsersController {

    String authorize(UserAuthDto userAuthDto);

    void registration(UserRegDto userRegDto);


}
