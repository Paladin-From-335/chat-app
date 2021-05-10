package com.github.chat.dto;


import java.util.Objects;

//TODO: add to userregdto name, lastname, phone

public class UserRegDto extends UserAuthDto {

    String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRegDto(String login, String password, String email) {
        super(login, password);
        this.email = email;
    }

    public UserRegDto(long id, String login, String password, String email) {
        super(id, login, password);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserRegDto that = (UserRegDto) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email);
    }

    @Override
    public String toString() {
        return "UserRegDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
