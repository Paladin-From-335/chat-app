package com.github.chat.entity;

import java.util.Objects;

public class SecretKey {

    private String email;

    private String secureCode;

    public SecretKey() {
    }

    public SecretKey(String email, String secureCode) {
        this.email = email;
        this.secureCode = secureCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecureCode() {
        return secureCode;
    }

    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecretKey secretKey = (SecretKey) o;
        return Objects.equals(email, secretKey.email) && Objects.equals(secureCode, secretKey.secureCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, secureCode);
    }

    @Override
    public String toString() {
        return "SecretKey{" +
                "email='" + email + '\'' +
                ", secureCode='" + secureCode + '\'' +
                '}';
    }
}
