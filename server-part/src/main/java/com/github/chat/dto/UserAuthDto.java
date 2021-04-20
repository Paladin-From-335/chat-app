package com.github.chat.dto;

import java.util.Objects;

public class UserAuthDto {
    long id;
    String login;
    String password;

    public UserAuthDto(String login, String password) {

        this.login = login;
        this.password = password;
    }

    public UserAuthDto(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public UserAuthDto() {
    }

    public UserAuthDto(UserRegDto userRegDto) {
        this.login = userRegDto.getLogin();
        this.password = userRegDto.getPassword();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return id == that.id && Objects.equals(login, that.login) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    @Override
    public String toString() {
        return "UserAuthDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
