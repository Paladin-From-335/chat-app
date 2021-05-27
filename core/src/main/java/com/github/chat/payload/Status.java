package com.github.chat.payload;

public enum Status {

    ONLINE("ONLINE"),
    OFFLINE("OFFLINE");

    private final String text;

    Status(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
