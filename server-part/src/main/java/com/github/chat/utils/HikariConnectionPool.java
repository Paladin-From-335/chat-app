package com.github.chat.utils;

//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.slf4j.*;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;

//public class HikariConnectionPool {
//
//    private static final Logger log = LoggerFactory.getLogger(HikariConnectionPool.class);
//
//        private static DataSource dataSource;
//
//        public static DataSource getDataSource(){
//            if(dataSource == null){
//                HikariConfig hikariConfig = new HikariConfig();
//
//                hikariConfig.setJdbcUrl("jdbc:postgresql://localhost/test");
//                hikariConfig.setUsername("postgres");
//                hikariConfig.setPassword("postgres");
//
//                hikariConfig.setMaximumPoolSize(10);
//                hikariConfig.setAutoCommit(false);
//                hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
//                hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
//                hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//
//                dataSource = new HikariDataSource(hikariConfig);
//            }
//            return dataSource;
//        }
//
//    public HikariConnectionPool() {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            DataSource dataSource = HikariConnectionPool.getDataSource();
//            connection = dataSource.getConnection();
//            preparedStatement = connection.prepareStatement("SELECT * FROM account");
//
//            System.out.println("The Connection obj is of Class: " + connection.getClass());
//
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()){
//                System.out.println(resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));
//            }
//        }
//        catch (Exception e){
//            try {
//
//                connection.rollback();
//            }
//            catch (SQLException sqlE){
//                sqlE.printStackTrace();
//            }
//            e.printStackTrace();
//        }
//    }
//
//}

