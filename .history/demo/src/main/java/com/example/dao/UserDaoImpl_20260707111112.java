package com.example.dao;


import java.util.List;

import com.example.config.HibernateUtil;
import com.example.entity.User;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;


public class UserDaoImpl implements UserDao{
    @Override
    public void create(User user){

        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
        catch(Exception e){
            if (transaction != null){
                if(transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK){
                    transaction.rollback();
                }
            }
        }
    }

    @Override
    public List<User> readAll(){
        Transaction transaction = null;
        List<User> users = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            users = session.createQuery("FROM User", User.class).list();
            transaction.commit();
        }
        catch(Exception e){
            if (transaction != null){
                if(transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK){
                    transaction.rollback();
                }
            }
        }
        return users;
    }

    @Override
    public User readById(int id){
        Transaction transaction = null;
        User user = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        }
        catch(Exception e){
            if (transaction != null){
                if(transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK){
                    transaction.rollback();
                }
            }
        }
        return user;
    }

    @Override
    public void update(User user){
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        }
        catch(Exception e){
            if (transaction != null){
                if(transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK){
                    transaction.rollback();
                }
            }
        }
    }

    @Override
    public void delete(User user){
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        }
        catch(Exception e){
            if (transaction != null){
                if(transaction.getStatus() == TransactionStatus.ACTIVE || transaction.getStatus() == TransactionStatus.MARKED_ROLLBACK){
                    transaction.rollback();
                }
            }
        }
    }
}
