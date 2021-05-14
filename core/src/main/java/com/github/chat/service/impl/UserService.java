package com.github.chat.service.impl;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.repository.impl.UserRepo;
import com.github.chat.service.IUsersService;

public class UserService implements IUsersService {

    private final UserRepo userRepo;


    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findById(long id) {
        return this.userRepo.findById(id);
    }

    @Override
    public User findByAuth(UserAuthDto authDto) {
        return this.userRepo.findByAuthDto(authDto);
    }

    @Override
    public User insert(UserRegDto regDto) {
        return this.userRepo.insert(regDto);
    }

    @Override
    public void delete(UserRegDto regDto) {
        this.userRepo.delete(regDto);
    }

    @Override
    public void update(UserRegDto regDto) {
        this.userRepo.update(regDto);
    }
}
