package com.github.chat.repository.impl;

import com.github.chat.entity.Message;
import com.github.chat.repository.IMessageRepository;

import javax.persistence.TypedQuery;
import java.util.List;

import static com.github.chat.utils.HibernateUtils.getSession;

public class MessageRepository extends AbstractRepository<Message, Long> implements IMessageRepository {

    @Override
    public List<Message> findAllByRoom(Long roomId) {
        TypedQuery<Message> query = getSession().createQuery("From Message where room_id=:roomId", Message.class);
        query.setParameter("roomId", roomId);
        return query.getResultList();
    }
}
