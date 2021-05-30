package com.github.chat.dto;

import com.github.chat.entity.User;

import java.util.Objects;

public class ForgotDto {

    private String email;

    private String password;

    private String confPassword;

    private String secureCode;

    private String hashpassword;

    private String salt;

    public ForgotDto() {
    }

    public ForgotDto(String email) {
        this.email = email;
    }

    public ForgotDto(String email, String password, String confPassword) {
        this.email = email;
        this.password = password;
        this.confPassword = confPassword;
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

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public String getSecureCode() {
        return secureCode;
    }

    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
    }

    public String getHashpassword() {
        return hashpassword;
    }

    public void setHashpassword(String hashpassword) {
        this.hashpassword = hashpassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForgotDto forgotDto = (ForgotDto) o;
        return Objects.equals(email, forgotDto.email) && Objects.equals(password, forgotDto.password) && Objects.equals(confPassword, forgotDto.confPassword) && Objects.equals(secureCode, forgotDto.secureCode) && Objects.equals(hashpassword, forgotDto.hashpassword) && Objects.equals(salt, forgotDto.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, confPassword, secureCode, hashpassword, salt);
    }

    @Override
    public String toString() {
        return "ForgotDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confPassword='" + confPassword + '\'' +
                '}';
    }

        public User toUser() {
            return new User(
                    null,
                    null,
                    null,
                    null,
                    this.password,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }
    }
