package com.github.chat.dto;

import com.github.chat.entity.User;

import java.util.Objects;

public class UserAuthDto {

    private String login;

    private String password;

    public UserAuthDto(){}

    public UserAuthDto(String login, String password){
        this.login = login;
        this.password = password;
    }

    public UserAuthDto(User user){
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

    public UserAuthDto(UserRegDto userRegDto) {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthDto that = (UserAuthDto) o;
        return Objects.equals(getLogin(), that.getLogin()) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword());
    }

    @Override
    public String toString() {
        return "UserAuthDto{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}