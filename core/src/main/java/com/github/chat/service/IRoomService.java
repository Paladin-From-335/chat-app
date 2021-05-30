package com.github.chat.service;

import com.github.chat.entity.Room;

import java.util.Collection;
import java.util.List;

public interface IRoomService {

    List<Room> findAll();

    void insert(Room room);

    void update(Room room);
}
