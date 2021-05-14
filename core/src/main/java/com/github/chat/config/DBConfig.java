package com.github.chat.config;

import com.github.chat.repository.impl.UserRepo;
import com.github.chat.service.impl.UserService;
import com.github.micro.orm.CustomJdbcTemplate;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DBConfig {

    private static final String HIKARI_PROPERTY_PATH = "core/src/main/resources/hikari.properties";

    public static DataSource getHikariDS() {
        HikariConfig config = new HikariConfig(HIKARI_PROPERTY_PATH);
        return new HikariDataSource(config);
    }

    public static UserRepo getUsersRepository() {
        return new UserRepo(new CustomJdbcTemplate(getHikariDS()));
    }

    public static UserService getUsersService() {
        return new UserService(getUsersRepository());
    }
}
