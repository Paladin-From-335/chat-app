package com.github.chat.service;

import com.github.chat.entity.RoomMembers;

import java.util.Collection;

public interface IRoomMemService {

    Collection<RoomMembers> findAllByRoom(Long room_id);

    void insert(RoomMembers roomMembers);

    void update(RoomMembers roomMembers);

}
