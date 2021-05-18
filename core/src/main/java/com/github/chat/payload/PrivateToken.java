package com.github.chat.payload;

import com.github.chat.entity.User;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PrivateToken implements Serializable {

    private final long lifeTime = 1800000;

    private Long userId;

    private String firstName;

    private String lastName;

    private Date expireIn;

    private Date createdAt;

    public PrivateToken(Long userId, String firstName, String lastName, Date expireIn, Date createdAt) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.expireIn = expireIn;
        this.createdAt = createdAt;
    }

    public PrivateToken(User user, Date expireIn, Date createdAt){
        this.userId = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.expireIn = expireIn;
        this.createdAt = createdAt;
    }

    public PrivateToken(User user){
        this.userId = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.createdAt = new Date(System.currentTimeMillis());
        this.expireIn = new Date(createdAt.getTime() + lifeTime);
    }
    public Long getId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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
        return Objects.equals(userId, privateToken.userId) && Objects.equals(getFirstName(), privateToken.getFirstName()) && Objects.equals(getLastName(), privateToken.getLastName()) && Objects.equals(getExpireIn(), privateToken.getExpireIn()) && Objects.equals(getCreatedAt(), privateToken.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, getFirstName(), getLastName(), getExpireIn(), getCreatedAt());
    }

    @Override
    public String toString() {
        return "Token{" +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", expireIn=" + expireIn +
                ", createdAt=" + createdAt +
                '}';
    }
}