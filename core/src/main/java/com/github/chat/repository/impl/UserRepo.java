package com.github.chat.repository.impl;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.repository.UserRowMapper;
import com.github.micro.orm.CustomJdbcTemplate;

public class UserRepo {

    private final CustomJdbcTemplate jdbcTemplate;

    public UserRepo(CustomJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByAuthDto(UserAuthDto userAuthorizationDto) {
        return jdbcTemplate.findBy(
                "SELECT * FROM user_table WHERE login = ? AND password = ?",
                UserRowMapper.getCustomRowMapperUser(),
                userAuthorizationDto.getLogin(),
                userAuthorizationDto.getPassword()
        );
    }

    public User findById(long id) {
        return jdbcTemplate.findBy(
                "SELECT * FROM user_table WHERE id = ?",
                UserRowMapper.getCustomRowMapperUser(),
                id
        );
    }

    public User insert(UserRegDto userRegistrationDto) {
        return jdbcTemplate.insert(
                "INSERT INTO user_table (firstName, lastName, email, login, password, phone, nickname) VALUES(?, ?, ?, ?, ?, ?, ?)",
                UserRowMapper.getCustomRowMapperUser(),
                userRegistrationDto.getFirstName(),
                userRegistrationDto.getLastName(),
                userRegistrationDto.getEmail(),
                userRegistrationDto.getLogin(),
                userRegistrationDto.getPassword(),
                userRegistrationDto.getPhone(),
                userRegistrationDto.getNickName()
        );
    }

    public void delete(UserRegDto userRegistrationDto) {
        jdbcTemplate.delete(
                "DELETE FROM user_table WHERE login = ?",
                userRegistrationDto.getLogin()
        );
    }

    public void update(UserRegDto userRegistrationDto) {
        jdbcTemplate.update(
                "UPDATE user_table " +
                        "SET firstName = ?, lastName = ?, email = ?, login = ?, password = ?, phone = ?" +
                        "WHERE login = ?",
                userRegistrationDto.getFirstName(),
                userRegistrationDto.getLastName(),
                userRegistrationDto.getEmail(),
                userRegistrationDto.getLogin(),
                userRegistrationDto.getPassword(),
                userRegistrationDto.getPhone()
        );
    }
}
