package com.example.dao;
import com.example.entity.User;
import java.util.List;


public interface UserDao {
    void create(User user);

    List<User> readAll();

    User readById(int id);

    void update(User user);

    void delete(User user);
}


