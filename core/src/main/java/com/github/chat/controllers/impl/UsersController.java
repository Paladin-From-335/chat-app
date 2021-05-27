package com.github.chat.controllers.impl;

import com.github.chat.controllers.IUsersController;
import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.exceptions.InternalServerError;
import com.github.chat.exceptions.UserAlreadyExistException;
import com.github.chat.payload.PrivateToken;
import com.github.chat.payload.PublicToken;
import com.github.chat.payload.Status;
import com.github.chat.service.IUsersService;
import com.github.chat.utils.JsonHelper;
import com.github.chat.utils.PrivateTokenProvider;
import com.github.chat.utils.PublicTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsersController implements IUsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final IUsersService userService;

    public UsersController(IUsersService userService) {
        this.userService = userService;
    }

    @Override
    public String authorize(UserAuthDto userAuthDto) {
        User user = this.userService.findByLogin(userAuthDto.getLogin());
        if (userAuthDto.getPassword().equals(user.getPassword())) {
            PrivateToken token = new PrivateToken(user);
            PublicToken publicToken = new PublicToken(user.getRole(), user.getNickname());
            String encodedTokens = PublicTokenProvider.publicEncode(publicToken) + "." + PrivateTokenProvider.encode(token);
            user.setStatus(Status.ONLINE);
            return JsonHelper.toJson(encodedTokens).orElseThrow(InternalServerError::new);

        }
        throw new NullPointerException();
    }

    @Override
    public void registration(UserRegDto userRegDto) {
        if (this.userService.findByEmail(userRegDto.getEmail()) == null) {
            userService.insert(userRegDto.toUser());
        }
        throw new UserAlreadyExistException();

    }
}
