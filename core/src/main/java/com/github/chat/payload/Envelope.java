package com.github.chat.payload;

import java.util.Objects;

public class Envelope {

    private Role role;

    private String token;

    public Envelope() {
    }

    public Envelope(Role role, String token) {
        this.role = role;
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Envelope envelope = (Envelope) o;
        return getRole() == envelope.getRole() && Objects.equals(getToken(), envelope.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole(), getToken());
    }

    @Override
    public String toString() {
        return "Envelope{" +
                "role=" + role +
                ", token='" + token + '\'' +
                '}';
    }
}
