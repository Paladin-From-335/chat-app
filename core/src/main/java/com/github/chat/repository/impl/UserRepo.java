package com.github.chat.repository.impl;

import com.github.chat.repository.IUsersRepo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collection;

public class UserRepo<T> implements IUsersRepo<T> {

    private final Class<T> clz;

    public UserRepo(Class<T> clz) {
        this.clz = clz;
    }

    @Override
    public Collection<T> findAll(Session session) {
        try (session) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(clz);
            criteria.from(clz);
            return session.createQuery(criteria).getResultList();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findBy(String field, Object value, Session session) {
        try (session) {
            Criteria criteria = session.createCriteria(clz);
            return (T) criteria.add(Restrictions.eq(field, value)).uniqueResult();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<T> findAllBy(String field, Object value, Session session) {
        try (session) {
            Criteria criteria = session.createCriteria(clz);
            return (Collection<T>) criteria.add(Restrictions.eq(field, value)).list();
        }
    }

    @Override
    public void save(T entity, Session session) {
        try (session) {
            Transaction tx1 = session.beginTransaction();
            session.save(entity);
            tx1.commit();
        }
    }

    @Override
    public void update(T entity, Session session) {
        try (session) {
            Transaction tx1 = session.beginTransaction();
            session.update(entity);
            tx1.commit();
        }
    }

    @Override
    public void delete(T entity, Session session) {
        try (session) {
            Transaction tx1 = session.beginTransaction();
            session.delete(entity);
            tx1.commit();
        }
    }
}
