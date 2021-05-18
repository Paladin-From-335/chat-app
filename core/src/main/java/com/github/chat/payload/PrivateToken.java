package com.github.chat.payload;

import com.github.chat.entity.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import static com.github.chat.utils.DateUtils.addMinutes;
import static com.github.chat.utils.DateUtils.getCurrentDate;

public class PrivateToken implements Serializable {

    private final Date date = new Date();

    private final String login;

    private final Role role;

    private final Date expireIn;

    private final Date createdAt;

    public PrivateToken(String login, Role role, Date expireIn, Date createdAt) {
        this.login = login;
        this.role = role;
        this.expireIn = expireIn;
        this.createdAt = createdAt;
    }

    public PrivateToken(User user, Date expireIn, Date createdAt) {
        this.login = user.getLogin();
        this.role = user.getRole();
        this.expireIn = expireIn;
        this.createdAt = createdAt;
    }

    public PrivateToken(User user) {
        this.login = user.getLogin();
        this.role = user.getRole();
        this.createdAt = getCurrentDate();
        this.expireIn = addMinutes(date, 30);
    }

    public String getLogin() {
        return login;
    }

    public Role getRole() {
        return role;
    }

    public Date getExpireIn() {
        return expireIn;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivateToken privateToken = (PrivateToken) o;
        return Objects.equals(date, privateToken.date) && Objects.equals(getLogin(), privateToken.getLogin()) && Objects.equals(getRole(), privateToken.getRole()) && Objects.equals(getExpireIn(), privateToken.getExpireIn()) && Objects.equals(getCreatedAt(), privateToken.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, getLogin(), getRole(), getExpireIn(), getCreatedAt());
    }

    @Override
    public String toString() {
        return "Token{" +
                "login='" + login + '\'' +
                ", role='" + role + '\'' +
                ", expireIn=" + expireIn +
                ", createdAt=" + createdAt +
                '}';
    }
}
