package com.github.chat.repository.impl;

import com.github.chat.entity.User;
import com.github.chat.repository.IUserRepository;

import javax.persistence.TypedQuery;
import java.util.List;

import static com.github.chat.utils.HibernateUtils.getSession;

public class UserRepository extends AbstractRepository<User, Long> implements IUserRepository {

    public User findOneByLogin(String login) {
        TypedQuery<User> query = getSession().createQuery("From User where login=:login", User.class);
        query.setParameter("login", login);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public User findOneByEmail(String email) {
        TypedQuery<User> query = getSession().createQuery("From User where email=:email", User.class);
        query.setParameter("email", email);
        return query.getResultStream().findFirst().orElse(null);
    }
}
