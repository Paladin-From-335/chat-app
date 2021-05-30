package com.github.chat.service;

import com.github.chat.entity.RoomMember;

import java.util.List;

public interface IRoomMemberService {

    List<RoomMember> findAllByRoom(Long roomId);

    void insert(RoomMember roomMember);

    void update(RoomMember roomMember);
}
