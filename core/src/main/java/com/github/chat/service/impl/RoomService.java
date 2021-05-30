package com.github.chat.service.impl;

import com.github.chat.entity.Room;
import com.github.chat.repository.IRoomRepository;
import com.github.chat.service.IRoomService;

import java.util.List;

public class RoomService implements IRoomService {

    private final IRoomRepository repository;

    public RoomService(IRoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Room> findAll() {
        return repository.findAll();
    }

    @Override
    public void insert(Room room) {
        repository.save(room);
    }

    @Override
    public void update(Room room) {
        repository.update(room);
    }
}
