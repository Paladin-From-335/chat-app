package com.github.chat.controllers.impl;

import com.github.chat.controllers.IUsersController;
import com.github.chat.dto.ForgotDto;
import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.exceptions.BadRequest;
import com.github.chat.exceptions.ForbiddenException;
import com.github.chat.exceptions.InternalServerError;
import com.github.chat.exceptions.UserAlreadyExistException;
import com.github.chat.exceptions.ValidationFieldException;
import com.github.chat.payload.PrivateToken;
import com.github.chat.payload.PublicToken;
import com.github.chat.payload.Status;
import com.github.chat.payload.Verification;
import com.github.chat.service.IUsersService;
import com.github.chat.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.chat.utils.ConstantEmail.forgotText;
import static com.github.chat.utils.ConstantEmail.regText;

public class UsersController implements IUsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final IUsersService userService;

    private final String secureCode = SecretCodeProvider.getSecretCode();

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PHONE_NUMBER_REGEX =
            Pattern.compile("^\\+?3?8?(\\d{10})$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_NAME_REGEX =
            Pattern.compile("[a-zA-z]+([ '-][a-zA-Z]+)*",
                    Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_NICKNAME_REGEX =
            Pattern.compile("^[a-zA-Z0-9]+([_-]?[a-zA-Z0-9])*$", Pattern.CASE_INSENSITIVE);

    public UsersController(IUsersService userService) {
        this.userService = userService;
    }

    @Override
    public String authorize(UserAuthDto userAuthDto) {
        User user = this.userService.findByLogin(userAuthDto.getLogin());
        if (user.getVerification().equals(Verification.unverified)) {
            throw new ForbiddenException();
        }
        if (user.getVerification().equals(Verification.verified)) {
            String checkHash = SaltProvider.encrypt(userAuthDto.getPassword() + user.getSalt());
            if (user.getHashpassword().equals(checkHash)) {
                PrivateToken token = new PrivateToken(user);
                PublicToken publicToken = new PublicToken(user.getRole(), user.getNickname());
                String encodedTokens = PublicTokenProvider.publicEncode(publicToken) + "." + PrivateTokenProvider.encode(token);
                user.setStatus(Status.ONLINE);
                return JsonHelper.toJson(encodedTokens).orElseThrow(InternalServerError::new);
            }
        }
            throw new NullPointerException();
    }

    @Override
    public void registration(UserRegDto payload) throws IOException {

        if (!isValidLastName(payload.getLastname())) {
            throw new ValidationFieldException("Last name incorrect format!");
        }
        if (!isValidFirstName(payload.getFirstname())) {
            throw new ValidationFieldException("First name incorrect format!");
        }

        if (!isValidEmail(payload.getEmail())) {
            throw new ValidationFieldException("Email incorrect format!");
        }

        if (!isValidNickname(payload.getNickname())) {
            throw new ValidationFieldException("Nickname incorrect format!");
        }

        if (!isValidPhone(payload.getPhone())) {
            throw new ValidationFieldException("Phone incorrect format!");
        }

        if (this.userService.findByLogin(payload.getLogin()) != null) {
            throw new UserAlreadyExistException();
        }
        payload.setSalt(SaltProvider.getRandomSalt());
        String hashpassword = payload.getPassword() + payload.getSalt();
        payload.setHashpassword(SaltProvider.encrypt(hashpassword));
        SendEmail.dispatchEmail(payload.getEmail(), regText, "http://localhost:8081/login/verification");
        this.userService.insert(payload.toUser());
    }

    @Override
    public void letterPost(ForgotDto forgotDto) throws IOException {
        User user = this.userService.findByEmail(forgotDto.getEmail());
        if (user != null) {
            String secureCode = SecretCodeProvider.getSecretCode();
            SendEmail.dispatchEmail(forgotDto.getEmail(), forgotText, secureCode);
        } else {
            throw new BadRequest();
        }
    }

    @Override
    public void updatePassword(ForgotDto forgotDto) {
        User user = this.userService.findByEmail(forgotDto.getEmail());
        if(user != null) {
            forgotDto.setSalt(SaltProvider.getRandomSalt());
            String hashpassword = forgotDto.getPassword() + forgotDto.getSalt();
            forgotDto.setHashpassword(SaltProvider.encrypt(hashpassword));
            user.setPassword(forgotDto.getPassword());
            user.setHashpassword(forgotDto.getHashpassword());
            user.setSalt(forgotDto.getSalt());
            this.userService.update(user);
        }
        throw new BadRequest();
    }

    @Override
    public void verificationEmail(UserRegDto userRegDto) {
        User user = this.userService.findByEmail(userRegDto.getEmail());
        if(userRegDto.getEmail().equals(user.getEmail())) {
           user.setVerification(Verification.verified);
            this.userService.update(user);

        }
        throw new NullPointerException();
    }

    private boolean isValidFirstName(String firstName) {
        Matcher matcher = VALID_NAME_REGEX.matcher(firstName);
        return matcher.find();
    }

    private boolean isValidLastName(String lastName) {
        Matcher matcher = VALID_NAME_REGEX.matcher(lastName);
        return matcher.find();
    }

    private boolean isValidPhone(String phone) {
        Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(phone);
        return matcher.find();
    }

    private boolean isValidEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    private boolean isValidNickname(String nickname) {
        Matcher matcher = VALID_NICKNAME_REGEX.matcher(nickname);
        return matcher.find();
    }

}