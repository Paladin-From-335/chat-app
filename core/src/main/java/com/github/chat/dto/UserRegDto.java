package com.github.chat.dto;

import com.github.chat.entity.User;

import java.util.Objects;

public class UserRegDto {

    private String firstname;

    private String lastname;

    private String login;

    private String password;

    private String confPassword;

    private String nickname;

    private String email;

    private String phone;

    public UserRegDto() {
    }

    public UserRegDto(String firstname, String lastName, String login, String password, String confPassword, String email, String phone) {
    }

    public UserRegDto(String firstname, String lastname, String login, String nickname, String password, String confPassword, String email, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.confPassword = confPassword;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
    }

    public UserRegDto(User user) {
        this.firstname = user.getFirstName();
        this.lastname = user.getLastName();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.confPassword = user.getPassword();
        this.nickname = user.getNickName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegDto that = (UserRegDto) o;
        return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(confPassword, that.confPassword) && Objects.equals(nickname, that.nickname) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, login, password, confPassword, nickname, email, phone);
    }

    @Override
    public String toString() {
        return "UserRegDto{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", confPassword='" + confPassword + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}