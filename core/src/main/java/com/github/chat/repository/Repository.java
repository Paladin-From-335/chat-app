package com.github.chat.repository;

import java.io.Serializable;
import java.util.List;

public interface Repository<T, K extends Serializable> {

    T save(T entity);

    T getOne(K id);

    T findOne(K id);

    List<T> findAll();

    void update(T entity);

    void delete(T entity);

    void delete(K id);

    void deleteAll();

    long count();

    boolean exists(K id);
}
