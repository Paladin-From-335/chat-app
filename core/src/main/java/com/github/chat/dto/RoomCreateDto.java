package com.github.chat.dto;

import com.github.chat.entity.User;

import java.util.List;
import java.util.Objects;

public class RoomCreateDto {

   private String room_name;

   private List<String> users;

   public RoomCreateDto() {
   }

   public RoomCreateDto(String room_name) {
      this.room_name = room_name;
   }

   public RoomCreateDto(String room_name, List<String> users) {
      this.room_name = room_name;
      this.users = users;
   }

   public String getRoom_name() {
      return room_name;
   }

   public void setRoom_name(String room_name) {
      this.room_name = room_name;
   }

   public List<String> getUsers() {
      return users;
   }

   public void setUsers(List<String> users) {
      this.users = users;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      RoomCreateDto that = (RoomCreateDto) o;
      return Objects.equals(room_name, that.room_name) && Objects.equals(users, that.users);
   }

   @Override
   public int hashCode() {
      return Objects.hash(room_name, users);
   }

   @Override
   public String toString() {
      return "RoomCreateDto{" +
              "room_name='" + room_name + '\'' +
              ", users=" + users +
              '}';
   }
}
