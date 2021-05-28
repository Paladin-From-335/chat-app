package com.github.chat.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "room_table", schema = "public", catalog = "d4p4rtluej9295")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long room_id;

    @Column(name = "room_name")
    private String room_name;

    public Long getRoom_id() {
        return room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(room_id, room.room_id) && Objects.equals(room_name, room.room_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room_id, room_name);
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + room_id +
                ", room_name='" + room_name + '\'' +
                '}';
    }
}
