package com.github.chat.service.impl;

import com.github.chat.entity.Room;
import com.github.chat.repository.IRepository;
import com.github.chat.service.IRoomService;
import com.github.chat.utils.HibernateUtils;

import java.util.Collection;

public class RoomService implements IRoomService {

    private final IRepository<Room> iRepository;

    public RoomService(IRepository<Room> iRepository) {
        this.iRepository = iRepository;
    }

    @Override
    public Collection<Room> findAll() {
        return this.iRepository.findAll(HibernateUtils.getSession());
    }

    @Override
    public void insert(Room room) {
        this.iRepository.save(room, HibernateUtils.getSession());
    }

    @Override
    public void update(Room room) {
        this.iRepository.update(room, HibernateUtils.getSession());
    }
}
