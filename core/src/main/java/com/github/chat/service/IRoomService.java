package com.github.chat.service;

import com.github.chat.entity.Room;

import java.util.Collection;

public interface IRoomService {

    Collection<Room> findAll();

    void insert(Room room);

    void update(Room room);
}
