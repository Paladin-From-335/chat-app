package com.github.chat.repository.impl;

import com.github.chat.entity.RoomMember;
import com.github.chat.repository.IRoomMemberRepository;

import javax.persistence.TypedQuery;
import java.util.List;

import static com.github.chat.utils.HibernateUtils.getSession;

public class RoomMemberRepository extends AbstractRepository<RoomMember, Long> implements IRoomMemberRepository {

    @Override
    public List<RoomMember> findAllByRoom(Long roomId) {
        TypedQuery<RoomMember> query = getSession().createQuery("From RoomMember where room_id=:roomId", RoomMember.class);
        query.setParameter("roomId", roomId);
        return query.getResultList();
    }
}
