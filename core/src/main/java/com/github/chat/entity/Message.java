package com.github.chat.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "message_table", schema = "public", catalog = "d4p4rtluej9295")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long message_id;

    @Column(name = "room_id")
    private Long room_id;

    @Column(name = "message")
    private String message;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "message_date")
    private Timestamp message_date;

    public Message(Long room_id, String message, String nickname, Timestamp message_date) {
        this.room_id = room_id;
        this.message = message;
        this.nickname = nickname;
        this.message_date = message_date;
    }

    public Message() {

    }

    public Long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Long message_id) {
        this.message_id = message_id;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Timestamp getMessage_date() {
        return message_date;
    }

    public void setMessage_date(Timestamp message_date) {
        this.message_date = message_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(message_id, message1.message_id) && Objects.equals(room_id, message1.room_id) && Objects.equals(message, message1.message) && Objects.equals(nickname, message1.nickname) && Objects.equals(message_date, message1.message_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message_id, room_id, message, nickname, message_date);
    }

    @Override
    public String toString() {
        return "Message{" +
                "message_id=" + message_id +
                ", room_id=" + room_id +
                ", message='" + message + '\'' +
                ", nickname='" + nickname + '\'' +
                ", message_date=" + message_date +
                '}';
    }
}