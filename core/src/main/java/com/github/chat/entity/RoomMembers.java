package com.github.chat.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "room_members_table", schema = "public", catalog = "d4p4rtluej9295")
public class RoomMembers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_mem_id")
    private Long room_mem_id;

    @Column(name = "room_id")
    private Long room_id;

    @Column(name = "user_id")
    private Long user_id;

    public Long getRoom_mem_id() {
        return room_mem_id;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomMembers that = (RoomMembers) o;
        return Objects.equals(room_mem_id, that.room_mem_id) && Objects.equals(room_id, that.room_id) && Objects.equals(user_id, that.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room_mem_id, room_id, user_id);
    }

    @Override
    public String toString() {
        return "RoomMembers{" +
                "room_mem_id=" + room_mem_id +
                ", room_id=" + room_id +
                ", user_id=" + user_id +
                '}';
    }
}
