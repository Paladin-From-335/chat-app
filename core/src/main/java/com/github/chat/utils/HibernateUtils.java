package com.github.chat.utils;

import com.github.chat.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;


public class HibernateUtils {

    private static SessionFactory sessionFactory;

    private static final Logger log = LoggerFactory.getLogger(HibernateUtils.class);

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try (InputStream inputStream = HibernateUtils.class.getResourceAsStream("/hibernate.properties")) {
                Configuration configuration = new Configuration().addInputStream(inputStream).configure();
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return sessionFactory;
    }

    public static Session getSession() {
        return getSessionFactory().openSession();
    }
}
