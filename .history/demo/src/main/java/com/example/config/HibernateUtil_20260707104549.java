package com.example.config;
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
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void closeSessionFactory(){
        if(sessionFactory != null)
            sessionFactory.close();
    }
}
