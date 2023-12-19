package jm.task.core.jdbc.util;

import org.hibernate.Session;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.lang.module.Configuration;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final SessionFactory sessionFactory;

    static {
        try {

            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgresDB");
            configuration.setProperty("hibernate.connection.username", "postgres");
            configuration.setProperty("hibernate.connection.password", "anehog");
            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            configuration.setProperty("hibernate.connection.provider_class", "org.hibernate.connection.C3P0ConnectionProvider");
            configuration.setProperty("hibernate.show_sql", "true");

            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgresDB";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "anehog";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgresDB";
//    private static final String JDBC_USER = "postgres";
//    private static final String JDBC_PASSWORD = "anehog";
//
//    public static Connection getConnection() {
//        try {
//            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
