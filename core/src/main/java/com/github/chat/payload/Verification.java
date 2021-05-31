package com.github.chat.payload;

public enum Verification {

    verified("verified"),
    unverified("unverified");

   private final String text;

    Verification(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Verification{" +
                "text='" + text + '\'' +
                '}';
    }
}
