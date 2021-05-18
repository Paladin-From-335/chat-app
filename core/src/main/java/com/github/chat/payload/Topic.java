package com.github.chat.payload;

public enum Topic {
    authorization("authorization"),
    messages("messages"),
    connect("connect"),
    disconnect("disconnect");

    private final String value;

    Topic(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Topic getTopic(String str) {
        for (Topic t: values()) {
            if(t.getValue().equals(str)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Topic is not exist");
    }
}
