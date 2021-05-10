package com.github.chat.repository.impl;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.Role;
import com.github.chat.entity.User;
import com.github.chat.repository.IUsersRepo;
import com.github.micro.orm.CustomJdbcTemplate;
import com.github.micro.orm.CustomRowExtractor;
import com.github.micro.orm.CustomRowMapper;

import java.util.ArrayList;
import java.util.List;

public class IUsersRepositoryImpl implements IUsersRepo {

    private static final CustomRowMapper<User> USER_CUSTOM_ROW_MAPPER = rs -> new User(
            rs.getLong(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getString(6),
            rs.getLong(7)
    );

    private static final CustomRowMapper<Role> ROLE_CUSTOM_ROW_MAPPER = rs -> new Role(
            rs.getLong(8),
            rs.getString(9)
    );

    private static final CustomRowExtractor<List<User>> LIST_CUSTOM_ROW_EXTRACTOR = rs -> {
        List<User> result = new ArrayList<>();
        while (rs.next()) {
            User user = USER_CUSTOM_ROW_MAPPER.rowMap(rs);
            Role role = ROLE_CUSTOM_ROW_MAPPER.rowMap(rs);
            user.setRole(role);
            result.add(user);
        }
        return result;
    };

    private final CustomJdbcTemplate jdbcTemplate;

    public IUsersRepositoryImpl(CustomJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
//    public User save(User user) {
//        this.jdbcTemplate.update("", user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
//                user.getEmail(), user.getLogin(), user.getPassword(), user.getPhone());
//        String query = "";
//        return this.jdbcTemplate.insert(query, USER_CUSTOM_ROW_MAPPER);
//    }
//
//    @Override
//    public List<User> findAll() {
//        String query = "user.id, user.first_name, user.last_name, user.email, user.login, user.password, user.phone, " +
//                "role.id, role.name where data > ?";
//        return this.jdbcTemplate.find(query, LIST_CUSTOM_ROW_EXTRACTOR, "");
//    }

    @Override
    public User insert(UserRegDto userRegDto) {
        String query = "";
        return this.jdbcTemplate.insert(query, USER_CUSTOM_ROW_MAPPER);
    }

    @Override
    public User findById(long id) {
        String query = "";
        return this.jdbcTemplate.findBy(query, USER_CUSTOM_ROW_MAPPER, id);
    }

    @Override
    public User findByAuth(UserAuthDto userAuthDto) {
        String query = "";
        return this.jdbcTemplate.findBy(query, USER_CUSTOM_ROW_MAPPER, userAuthDto.getLogin(), userAuthDto.getPassword());
    }

    @Override
    public void update(UserRegDto userRegDto) {
        String query = "";
        this.jdbcTemplate.delete(query, userRegDto.getEmail(), userRegDto.getLogin(), userRegDto.getPassword());
    }

    @Override
    public void delete(UserRegDto userRegDto) {
        String query = "";
        this.jdbcTemplate.delete(query, userRegDto.getLogin());
    }
}
