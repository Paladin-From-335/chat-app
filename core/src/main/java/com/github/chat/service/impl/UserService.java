package com.github.chat.service.impl;

import com.github.chat.entity.User;
import com.github.chat.repository.IRepository;
import com.github.chat.service.IUsersService;
import com.github.chat.utils.HibernateUtils;

import java.util.Collection;

public class UserService implements IUsersService {

    private final IRepository<User> iRepository;

    public UserService(IRepository<User> iRepository) {
        this.iRepository = iRepository;
    }


    @Override
    public Collection<User> findAll() {
        return this.iRepository.findAll(HibernateUtils.getSession());
    }

    @Override
    public User findById(Long user_id) {
        return this.iRepository.findBy("user_id", user_id, HibernateUtils.getSession());
    }

    @Override
    public User findByLogin(String login) {
        return this.iRepository.findBy("login", login, HibernateUtils.getSession());
    }

    @Override
    public User findByEmail(String email) {
        return this.iRepository.findBy("email", email, HibernateUtils.getSession());
    }

    @Override
    public void insert(User user) {
        this.iRepository.save(user, HibernateUtils.getSession());
    }

    @Override
    public void update(User user) {
        this.iRepository.update(user, HibernateUtils.getSession());
    }
}