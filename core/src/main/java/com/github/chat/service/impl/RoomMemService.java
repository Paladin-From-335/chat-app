package com.github.chat.service.impl;

import com.github.chat.entity.RoomMembers;
import com.github.chat.repository.IRepository;
import com.github.chat.service.IRoomMemService;
import com.github.chat.utils.HibernateUtils;

import java.util.Collection;

public class RoomMemService implements IRoomMemService {

    private final IRepository<RoomMembers> iRepository;

    public RoomMemService(IRepository<RoomMembers> iRepository) {
        this.iRepository = iRepository;
    }

    @Override
    public Collection<RoomMembers> findAllByRoom(Long room_id) {
        return this.iRepository.findAllBy("room_id", room_id, HibernateUtils.getSession());
    }

    @Override
    public void insert(RoomMembers roomMembers) {
        this.iRepository.save(roomMembers, HibernateUtils.getSession());
    }

    @Override
    public void update(RoomMembers roomMembers) {
        this.iRepository.update(roomMembers, HibernateUtils.getSession());
    }
}
