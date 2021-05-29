package com.github.chat.entity;

import com.github.chat.payload.Role;
import com.github.chat.payload.Status;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_table", schema = "public", catalog = "d4p4rtluej9295")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Column(name = "role")
    private Role role;

    @Column(name = "status")
    private Status status;

    @Column(name = "hashpassword")
    private String hashpassword;

    @Column(name = "salt")
    private String salt;

    public User(
            Long user_id,
            String firstname,
            String lastname,
            String login,
            String password,
            String nickname,
            String email,
            String phone,
            Role role,
            Status status) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.nickname = nickname;
        this.role = role;
        this.status = status;
    }

    public User() {
    }

    public User(Long user_id, String firstname, String lastname, String login, String password, String nickname, String email, String phone, Role role, Status status, String hashpassword, String salt) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.nickname = nickname;
        this.role = role;
        this.status = status;
        this.hashpassword = hashpassword;
        this.salt = salt;
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
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

    public String getPhone() {
        return phone;
    }

    public String getNickname() {
        return nickname;
    }

    public Role getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_id == user.user_id && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname) && Objects.equals(email, user.email) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(phone, user.phone) && Objects.equals(nickname, user.nickname) && role == user.role && status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, firstname, lastname, email, login, password, phone, nickname, role, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}