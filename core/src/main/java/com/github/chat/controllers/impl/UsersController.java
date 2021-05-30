package com.github.chat.controllers.impl;

import com.github.chat.controllers.IUsersController;
import com.github.chat.dto.ForgotDto;
import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.exceptions.BadRequest;
import com.github.chat.exceptions.InternalServerError;
import com.github.chat.exceptions.UserAlreadyExistException;
import com.github.chat.payload.PrivateToken;
import com.github.chat.payload.PublicToken;
import com.github.chat.payload.Status;
import com.github.chat.service.IUsersService;
import com.github.chat.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;


import static com.github.chat.utils.ConstantEmail.forgotText;
import static com.github.chat.utils.ConstantEmail.regText;

public class UsersController implements IUsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final IUsersService userService;

    private HashMap<String, String> secretKey;

    public UsersController(IUsersService userService) {
        this.userService = userService;
    }

    @Override
    public String authorize(UserAuthDto userAuthDto) {
        User user = this.userService.findByLogin(userAuthDto.getLogin());
        String checkHash = SaltProvider.encrypt(userAuthDto.getPassword() + user.getSalt());
        if (user.getHashpassword().equals(checkHash)) {
            PrivateToken token = new PrivateToken(user);
            PublicToken publicToken = new PublicToken(user.getRole(), user.getNickname());
            String encodedTokens = PublicTokenProvider.publicEncode(publicToken) + "." + PrivateTokenProvider.encode(token);
            user.setStatus(Status.ONLINE);
            return JsonHelper.toJson(encodedTokens).orElseThrow(InternalServerError::new);
        }
        throw new NullPointerException();
    }

    @Override
    public void registration(UserRegDto payload) throws IOException {
        if (this.userService.findByLogin(payload.getLogin()) != null) {
            throw new UserAlreadyExistException();
        }
        payload.setSalt(SaltProvider.getRandomSalt());
        String hashpassword = payload.getPassword() + payload.getSalt();
        payload.setHashpassword(SaltProvider.encrypt(hashpassword));
        SendEmail.dispatchEmail(payload.getEmail(), regText, "http://localhost:8081/chat");
        this.userService.insert(payload.toUser());
    }

    @Override
    public String forgotReq(ForgotDto forgotDto) throws IOException {
        User user = this.userService.findByEmail(forgotDto.getEmail());
        if (user != null) {
            String secureCode = SecretCodeProvider.getSecretCode();
            SendEmail.dispatchEmail(forgotDto.getEmail(), forgotText, secureCode);
            secretKey.put(secureCode, forgotDto.getEmail());
            if (secretKey.containsKey(secureCode) && secretKey.containsValue(forgotDto.getEmail())) {
                String checkHash = SaltProvider.encrypt(forgotDto.getPassword() + user.getSalt());
                forgotDto.setHashpassword(checkHash);
                this.userService.update(forgotDto.toUser());
            }
            return secureCode;
        } else {
            throw new BadRequest();
        }
    }
}