package com.github.chat.service.impl;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.repository.IUsersRepo;
import com.github.chat.repository.impl.UserRepo;
import com.github.chat.service.IUsersService;
import com.github.chat.utils.HibernateUtils;

import java.util.Collection;

public class UserService implements IUsersService {

    private final IUsersRepo<User> iUsersRepo;

    public UserService(IUsersRepo<User> iUsersRepo) {
        this.iUsersRepo = iUsersRepo;
    }


    @Override
    public Collection<User> findAll() {
        return this.iUsersRepo.findAll(HibernateUtils.getSession());
    }

    @Override
    public User findById(Long id) {
        return this.iUsersRepo.findBy("id", id, HibernateUtils.getSession());
    }

    @Override
    public User findByLogin(String login) {
        return this.iUsersRepo.findBy("login", login, HibernateUtils.getSession());
    }

    @Override
    public User findByEmail(String email) {
        return this.iUsersRepo.findBy("email", email, HibernateUtils.getSession());
    }

    @Override
    public void insert(User user) {
        this.iUsersRepo.save(user, HibernateUtils.getSession());
    }

    @Override
    public void update(User user) {
        this.iUsersRepo.update(user, HibernateUtils.getSession());
    }
}