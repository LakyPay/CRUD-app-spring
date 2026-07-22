package com.example;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        User testUser;
        testUser.setName("test");
        testUser.setEmail("test");
        testUser.setAge(23);
        testUser.setCreatedAt(LocalDateTime.now());
        UserDaoImpl.create(testUser);
        System.out.println(UserDaoImpl.readById(1).getName());
    }
}