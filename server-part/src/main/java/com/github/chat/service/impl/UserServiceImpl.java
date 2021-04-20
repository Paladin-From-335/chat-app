package com.github.chat.service.impl;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.repository.IUsersRepo;
import com.github.chat.service.IUsersService;

public class UserServiceImpl implements IUsersService {

    private final IUsersRepo iUsersRepo;

    public UserServiceImpl(IUsersRepo iUsersRepo) {
        this.iUsersRepo = iUsersRepo;
    }

    @Override
    public User create(UserRegDto userRegDto) {
        return this.iUsersRepo.insert(userRegDto);
    }

    @Override
    public User findById(long id) {
        return this.iUsersRepo.findById(id);
    }

    @Override
    public User findByAuth(UserAuthDto userAuthDto) {
        return this.iUsersRepo.findByAuth(userAuthDto);
    }

    @Override
    public void update(UserRegDto userRegDto) {
        this.iUsersRepo.update(userRegDto);
    }

    @Override
    public void delete(UserRegDto userRegDto) {
        this.iUsersRepo.delete(userRegDto);
    }
}
