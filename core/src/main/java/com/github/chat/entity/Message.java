package com.github.chat.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    private final String message;

    private final String nickname;

    private final String hours;

    private final String days;

    public Message(String message, String nickname, Date date) {
        this.message = message;
        this.nickname = nickname;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        this.hours = simpleDateFormat.format(date);
        simpleDateFormat.applyLocalizedPattern("dd/MM/yyyy");
        this.days = simpleDateFormat.format(date);
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", nickname='" + nickname + '\'' +
                ", hours='" + hours + '\'' +
                ", days='" + days + '\'' +
                '}';
    }
}
