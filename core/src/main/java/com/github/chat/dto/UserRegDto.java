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

    public UserRegDto(String firstName, String lastName, String login, String password, String confPassword, String email, String phone){}

    public UserRegDto(String firstname, String lastname, String login, String nickname,  String password, String confPassword, String email, String phone){
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.confPassword = confPassword;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
    }

    public UserRegDto(User user){
        this.firstname = user.getFirstName();
        this.lastname = user.getLastName();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.confPassword = user.getPassword();
        this.nickname = user.getNickName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
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

    public String getNickName() { return nickname; }

    public void setNickName(String nickName) { this.nickname = nickname; }

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhoneNum(long phoneNum) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegDto that = (UserRegDto) o;
        return phone == that.phone &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(confPassword, that.confPassword) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(email, that.email);
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
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", confPassword='" + confPassword + '\'' +
                ", phoneNum=" + phone +
                '}';
    }
}