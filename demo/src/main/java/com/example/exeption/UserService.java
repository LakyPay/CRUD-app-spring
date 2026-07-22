package com.example.exeption;

import java.util.List;

import com.example.entity.User;

public interface UserService {
    void create(User user);
    List<User> readAll();
    User readById(int id);
    void update(User user);
    void delete(User user);
}
