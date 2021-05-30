package com.github.chat.repository;

import com.github.chat.entity.RoomMember;

import java.util.List;

public interface IRoomMemberRepository extends Repository<RoomMember, Long> {

    List<RoomMember> findAllByRoom(Long roomId);
}
