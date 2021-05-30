package com.github.chat.service.impl;

import com.github.chat.entity.RoomMember;
import com.github.chat.repository.IRoomMemberRepository;
import com.github.chat.service.IRoomMemberService;

import java.util.List;

public class RoomMemberService implements IRoomMemberService {

    private final IRoomMemberRepository roomMemberRepository;

    public RoomMemberService(IRoomMemberRepository roomMemberRepository) {
        this.roomMemberRepository = roomMemberRepository;
    }

    @Override
    public List<RoomMember> findAllByRoom(Long roomId) {
        return roomMemberRepository.findAllByRoom(roomId);
    }

    @Override
    public void insert(RoomMember roomMember) {
        roomMemberRepository.save(roomMember);
    }

    @Override
    public void update(RoomMember roomMember) {
        roomMemberRepository.update(roomMember);
    }
}
