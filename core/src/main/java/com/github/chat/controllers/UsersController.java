package com.github.chat.controllers;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.exceptions.InternalServerError;
import com.github.chat.exceptions.UserAlreadyExistException;
import com.github.chat.payload.Envelope;
import com.github.chat.payload.PrivateToken;
import com.github.chat.service.impl.UserService;
import com.github.chat.utils.JsonHelper;
import com.github.chat.utils.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsersController implements IUsersController{

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String authorize(UserAuthDto userAuthDto) {
        User user = this.userService.findByLogin(userAuthDto.getLogin());
        PrivateToken token = new PrivateToken(user);
        String encodedToken = TokenProvider.encode(token);
        Envelope env = new Envelope(user.getRole(), JsonHelper.toJson(encodedToken).orElseThrow(InternalServerError::new));
        return JsonHelper.toJson(env).orElseThrow(InternalServerError::new);
    }

    @Override
    public void registration(UserRegDto userRegDto) {
//        if (this.userService.findByEmail(userRegDto.getEmail()) != null) {
//            throw new UserAlreadyExistException();
//        }
        userService.insert(userRegDto.toUser());
    }
}
