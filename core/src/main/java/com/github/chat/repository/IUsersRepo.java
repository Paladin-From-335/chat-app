package com.github.chat.repository;

import org.hibernate.Session;

import java.util.Collection;

public interface IUsersRepo<T> {

    Collection<T> findAll(Session session);

    T findBy(String field, Object value, Session session);

    Collection<T> findAllBy(String field, Object value, Session session);

    void save(T entity, Session session);

    void update(T entity, Session session);

    void delete(T entity, Session session);


}
