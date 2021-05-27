package com.github.chat.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "message_table", schema = "public", catalog = "d5ld3iihtli9rs")
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

    @Column(name = "hours")
    private String hours;

    @Column(name = "days")
    private String days;

    public Message(Long message_id, Long room_id, String message, String nickname, String hours, String days) {
        this.message_id = message_id;
        this.room_id = room_id;
        this.message = message;
        this.nickname = nickname;
        this.hours = hours;
        this.days = days;
    }

    public Message() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(message_id, message1.message_id) && Objects.equals(room_id, message1.room_id) && Objects.equals(message, message1.message) && Objects.equals(nickname, message1.nickname) && Objects.equals(hours, message1.hours) && Objects.equals(days, message1.days);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message_id, room_id, message, nickname, hours, days);
    }

    @Override
    public String toString() {
        return "Message{" +
                "message_id=" + message_id +
                ", room_id=" + room_id +
                ", message='" + message + '\'' +
                ", nickname='" + nickname + '\'' +
                ", hours='" + hours + '\'' +
                ", days='" + days + '\'' +
                '}';
    }
}
