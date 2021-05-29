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

    private final String nickname;

    private final Role role;

    private final Date expireIn;

    private final Date createdAt;

    public PrivateToken(String login, String nickname, Role role, Date expireIn, Date createdAt) {
        this.login = login;
        this.nickname = nickname;
        this.role = role;
        this.expireIn = expireIn;
        this.createdAt = createdAt;
    }

    public PrivateToken(User user, Date expireIn, Date createdAt) {
        this.login = user.getLogin();
        this.nickname = user.getNickname();
        this.role = user.getRole();
        this.expireIn = expireIn;
        this.createdAt = createdAt;
    }

    public PrivateToken(User user) {
        this.login = user.getLogin();
        this.nickname = user.getNickname();
        this.role = user.getRole();
        this.createdAt = getCurrentDate();
        this.expireIn = addMinutes(date, 30);
    }

    public String getLogin() {
        return login;
    }

    public String getNickname() {
        return nickname;
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
        PrivateToken token = (PrivateToken) o;
        return Objects.equals(login, token.login) && Objects.equals(nickname, token.nickname) && role == token.role && Objects.equals(expireIn, token.expireIn) && Objects.equals(createdAt, token.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, nickname, role, expireIn, createdAt);
    }

    @Override
    public String toString() {
        return "PrivateToken{" +
                "login='" + login + '\'' +
                ", nickname='" + nickname + '\'' +
                ", role=" + role +
                ", expireIn=" + expireIn +
                ", createdAt=" + createdAt +
                '}';
    }
}
