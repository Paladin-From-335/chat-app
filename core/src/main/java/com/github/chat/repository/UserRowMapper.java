package com.github.chat.repository;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.micro.orm.CustomRowMapper;

public class UserRowMapper {

    private static CustomRowMapper<UserAuthDto> customRowMapperAuth;

    private static CustomRowMapper<UserRegDto> customRowMapperReg;

    private static CustomRowMapper<User> customRowMapperUser;

    public static CustomRowMapper<UserAuthDto> getCustomRowMapperAuth() {
        if (customRowMapperAuth == null) {
            customRowMapperAuth = rs -> new UserAuthDto(
                    rs.getString("login"),
                    rs.getString("password"));
        }
        return customRowMapperAuth;
    }

    public static CustomRowMapper<UserRegDto> getCustomRowMapperReg() {
        if (customRowMapperReg == null) {
            customRowMapperReg = rs -> new UserRegDto(
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("confirmPassword"),
                    rs.getString("nickname"),
                    rs.getString("email"),
                    rs.getString("phone")
            );
        }
        return customRowMapperReg;
    }

    public static CustomRowMapper<User> getCustomRowMapperUser() {
        if (customRowMapperUser == null) {
            customRowMapperUser = rs -> new User(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("nickname"),
                    rs.getString("phone"));
        }
        return customRowMapperUser;
    }
}
