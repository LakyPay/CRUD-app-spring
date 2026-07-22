package com.example;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.example.entity.User;
import com.example.dao.*;

public class Main {
    public static void main(String[] args) {
        User testUser = new User();
        testUser.setName("test");
        testUser.setEmail("test");
        testUser.setAge(23);
        testUser.setCreatedAt(LocalDateTime.now());
        Scanner scanner = new Scanner(System.in);

        UserDaoImpl userDaoImpl = new UserDaoImpl();
        userDaoImpl.create(testUser);
        System.out.println(userDaoImpl.readById(1).getName());
        String text = scanner.nextLine();
        scanner.close();
    }
}