package com.github.chat.service.impl;

import com.github.chat.entity.User;
import com.github.chat.repository.IUserRepository;
import com.github.chat.repository.impl.UserRepository;
import com.github.chat.service.IUsersService;
import com.github.chat.utils.HibernateUtils;

import java.util.List;

public class UserService implements IUsersService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public void insert(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }
}