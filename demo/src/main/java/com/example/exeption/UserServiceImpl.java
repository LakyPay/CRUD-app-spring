package com.example.exeption;

import java.util.List;

import com.example.dao.UserDao;
import com.example.entity.User;

public class UserServiceImpl implements UserService{
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public void create(User user){
        userDao.create(user);
    }

    @Override
    public List<User> readAll(){
        return userDao.readAll();
    }

    @Override
    public User readById(int id){
        return userDao.readById(id);
    }

    @Override
    public void update(User user){
        userDao.update(user);
    }

    @Override
    public void delete(User user){
        userDao.delete(user);
    }
}
