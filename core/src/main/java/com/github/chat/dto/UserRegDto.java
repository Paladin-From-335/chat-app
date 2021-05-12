package com.github.chat.dto;

import java.util.Objects;

public class UserRegDto {

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    private String confPassword;

    private String nickName;

    private String email;

    private long phoneNum;

    public UserRegDto(){}

    public UserRegDto(String firstName, String lastName, String login, String password, String confPassword, String nickName, String email, long phoneNum){
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.confPassword = confPassword;
        this.nickName = nickName;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getNickName() { return nickName; }

    public void setNickName(String nickName) { this.nickName = nickName; }

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

    public long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegDto that = (UserRegDto) o;
        return phoneNum == that.phoneNum && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName) && Objects.equals(login, that.login)
                && Objects.equals(password, that.password) && Objects.equals(confPassword, that.confPassword)
                && Objects.equals(nickName, that.nickName) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, login, password, confPassword, nickName, email, phoneNum);
    }

    @Override
    public String toString() {
        return "UserRegDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", confPassword='" + confPassword + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum=" + phoneNum +
                '}';
    }
}