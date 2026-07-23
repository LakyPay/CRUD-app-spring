package com.example.exeption;

import java.util.List;
import java.util.Optional;

import com.example.entity.User;
import com.example.entity.UserDto;

public interface UserService {
    void create(UserDto user);
    Iterable<User> readAll();
    Optional<User> readById(int id);
    void update(UserDto user);
    void delete(int id);
}
