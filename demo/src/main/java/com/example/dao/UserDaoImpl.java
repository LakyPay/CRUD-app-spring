package com.example.dao;


import java.util.List;

import com.example.config.HibernateUtil;
import com.example.entity.User;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserDaoImpl implements UserDao{
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(User user){

        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            logger.info("Создан пользователь");
        }
        catch(Exception e){
            if (transaction != null){
                if(transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK){
                    logger.error("транзакция прервана", e);
                    transaction.rollback();
                }
            }
            logger.error("транзакция не произошла", e);
        }
    }

    @Override
    public List<User> readAll(){
        Transaction transaction = null;
        List<User> users = null;

        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User", User.class).list();
            transaction.commit();
            logger.info("выведены пользователи");
        }
        catch(Exception e){
            if (transaction != null){
                if(transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK){
                    transaction.rollback();
                    logger.error("транзакция прервана", e);
                }
            }
            logger.error("транзакция не произошла", e);
        }
        return users;
    }

    @Override
    public User readById(int id){
        Transaction transaction = null;
        User user = null;

        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
            logger.info("выведен пользователь");
        }
        catch(Exception e){
            if (transaction != null){
                if(transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK){
                    transaction.rollback();
                    logger.error("транзакция прервана", e);
                }
            }
            logger.error("транзакция не произошла", e);
        }
        return user;
    }

    @Override
    public void update(User user){
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            logger.info("изменён пользователь");
        }
        catch(Exception e){
            if (transaction != null){
                if(transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK){
                    transaction.rollback();
                    logger.error("транзакция прервана", e);
                }
            }
            logger.error("транзакция не произошла", e);
        }
    }

    @Override
    public void delete(User user){
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
            logger.info("удалён пользователь");
        }
        catch(Exception e){
            if (transaction != null){
                if(transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK){
                    transaction.rollback();
                    logger.error("транзакция прервана", e);
                }
            }
            logger.error("транзакция не произошла", e);
        }
    }
}
