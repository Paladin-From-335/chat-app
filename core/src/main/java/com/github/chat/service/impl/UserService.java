package com.github.chat.service.impl;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.repository.IUsersRepo;
import com.github.chat.service.IUsersService;

import java.util.List;

public class UserService implements IUsersService {

    private final IUsersRepo iUsersRepo;

    public UserService(IUsersRepo iUsersRepo) {
        this.iUsersRepo = iUsersRepo;
    }

    @Override
    public User insert(UserRegDto userRegDto) {
        return this.iUsersRepo.insert(userRegDto);
    }

    @Override
    public User create(UserRegDto userRegDto) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return this.iUsersRepo.findAll();
    }

    @Override
    public User findByReg(UserRegDto userRegDto) {
        return this.iUsersRepo.findByReg(userRegDto);
    }

    @Override
    public User findByAuth(UserAuthDto userAuthDto) {
        return this.iUsersRepo.findByAuth(userAuthDto);
    }

    @Override
    public void update(User user) {
        this.iUsersRepo.update(user);
    }

    @Override
    public void delete(User user) {
        this.iUsersRepo.delete(user);
    }
}
