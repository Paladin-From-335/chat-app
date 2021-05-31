package com.github.chat.dto;

import com.github.chat.entity.User;

public class ForgotDto {

    private String email;

    private String password;

    private String secureCode;

    private String salt;

    private String hashpassword;

    public ForgotDto() {
    }

    public ForgotDto(String email) {
        this.email = email;
    }

    public ForgotDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public ForgotDto(String email, String password, String secureCode, String salt, String hashpassword) {
        this.email = email;
        this.password = password;
        this.secureCode = secureCode;
        this.salt = salt;
        this.hashpassword = hashpassword;
    }

    public ForgotDto(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.hashpassword = user.getHashpassword();
        this.salt = user.getSalt();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecureCode() {
        return secureCode;
    }

    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHashpassword() {
        return hashpassword;
    }

    public void setHashpassword(String hashpassword) {
        this.hashpassword = hashpassword;
    }

    @Override
    public String toString() {
        return "ForgotDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", secureCode='" + secureCode + '\'' +
                ", salt='" + salt + '\'' +
                ", hashpassword='" + hashpassword + '\'' +
                '}';
    }

    public User toUser() {
        return new User(
                null,
                toUser().getFirstname(),
                toUser().getLastname(),
                toUser().getEmail(),
                toUser().getLogin(),
                this.password,
                toUser().getPhone(),
                toUser().getNickname(),
                toUser().getRole(),
                toUser().getStatus(),
                this.hashpassword,
                this.salt,
                toUser().getVerification()
        );
    }
}
