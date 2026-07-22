package com.example.app;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.example.entity.User;
import com.example.dao.*;

public class ConsoleUI {

    public static void mainMenu(){
        boolean exitChecker = false;
        Scanner scanner = new Scanner(System.in);
        while(exitChecker){
            System.out.println("1. Создать пользователя\r2. Показать всех пользователей\r3. Найти пользователя по id\r4. Изменить пользователя\r5. Удалить пользователя\r0. Выход");
            System.out.println("\r\rВыберите действие:");
            switch (scanner.nextLine()) {
                case "1":{
                    User user = new User();
                    System.out.println("Введите имя:");
                    user.setName(scanner.nextLine());
                    System.out.println("Введите почту:");
                    user.setEmail(scanner.nextLine());
                    System.out.println("Введите возраст:");
                    user.setAge(Integer.parseInt(scanner.nextLine()));
                    user.setCreatedAt(LocalDateTime.now());
                    UserDaoImpl userDaoImpl = new UserDaoImpl();
                    userDaoImpl.create(user);
                    System.out.println("Пользователь создан");
                }
                case "2":{
                    UserDaoImpl userDaoImpl = new UserDaoImpl();
                    System.out.println("Id | Name | Age | Email | Created_at");
                    for (User user : userDaoImpl.readAll()) {
                        System.out.println(user.getId() + " | " + user.getName() + " | " + user.getAge() + " | " + user.getEmail() + " | " + user.getCreatedAt());
                    }
                }
                case "3":{
                    UserDaoImpl userDaoImpl = new UserDaoImpl();
                    System.out.println("Введите Id");
                    try{
                        User user = userDaoImpl.readById(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Id | Name | Age | Email | Created_at");
                        System.out.println(user.getId() + " | " + user.getName() + " | " + user.getAge() + " | " + user.getEmail() + " | " + user.getCreatedAt());
                    }
                    catch(Exception e){
                        System.out.println("Введите целочисленное значение!");
                    }
                }
                case "4":{
                    
                    try{
                        User user = new User();
                        System.out.println("Введите id:");
                        user.setId(Integer.parseInt(scanner.nextLine()));
                        System.out.println("Введите имя:");
                        user.setName(scanner.nextLine());
                        System.out.println("Введите почту:");
                        user.setEmail(scanner.nextLine());
                        System.out.println("Введите возраст:");
                        user.setAge(Integer.parseInt(scanner.nextLine()));
                        user.setCreatedAt(LocalDateTime.now());
                        UserDaoImpl userDaoImpl = new UserDaoImpl();
                        userDaoImpl.create(user);
                        System.out.println("Пользователь создан");
                    }
                    catch(Exception e){
                        System.out.println("Введите целочисленное значение!");
                    }
                }
                case "5":{
                    
                }
                case "0":{
                    
                }
                default:
                    break;
            }
        }
    }
}
