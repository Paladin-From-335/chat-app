package com.github.chat.config;

public class DBConfig {

    private static HikariConfig config = new HikariConfig();

    private static HikariDataSource dataSource;

    static {
        config.setJdbcUrl("jdbc:postgresql://localhost:5431/");
        config.setUsername("postgres");
        config.setPassword("postgres");
        dataSource = new HikariDataSource(config);
    }

    public static HikariConfig getConfig() {
        return config;
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }
}
