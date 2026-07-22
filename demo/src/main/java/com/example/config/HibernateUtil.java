package com.example.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static{
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
        .configure("hibernate.cfg.xml")
        .build();

        try{
            sessionFactory = new MetadataSources(registry)
            .buildMetadata()
            .buildSessionFactory();
        }
        catch(Exception e){
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static SessionFactory createSessionFactory(String url, String login, String password){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
        .configure("hibernate.cfg.xml")
        .applySetting("hibernate.connection.url", url)
        .applySetting("hibernate.connection.username", login)
        .applySetting("hibernate.connection.password", password)
        .build();
        SessionFactory testSessionFactory = sessionFactory;

        try{
            testSessionFactory = new MetadataSources(registry)
            .buildMetadata()
            .buildSessionFactory();
        }
        catch(Exception e){
            StandardServiceRegistryBuilder.destroy(registry);
            throw new RuntimeException(e);
        }
        return testSessionFactory;
    }
    public static void closeSessionFactory(){
        if(sessionFactory != null)
            sessionFactory.close();
    }
}
