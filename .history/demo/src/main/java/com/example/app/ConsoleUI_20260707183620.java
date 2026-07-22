package com.example.app;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.example.entity.User;
import com.example.dao.*;

public class ConsoleUI {

    public static void mainMenu(){
        boolean exitChecker = true;
        Scanner scanner = new Scanner(System.in);
        while(exitChecker){
            System.out.println("1. Создать пользователя");
            System.out.println("2. Показать всех пользователей");
            System.out.println("3. Найти пользователя по id");
            System.out.println("4. Изменить пользователя");
            System.out.println("5. Удалить пользователя");
            System.out.println("0. Выход");
            System.out.println("");
            System.out.println("\r\rВыберите действие:");
            String scanText = scanner.nextLine();

            UserDaoImpl userDaoImpl = new UserDaoImpl();
            switch (scanText) {
                case "1":{
                    
                    try{
                        User user = new User();
                        System.out.println("Введите имя:");
                        user.setName(scanner.nextLine());
                        System.out.println("Введите почту:");
                        user.setEmail(scanner.nextLine());
                        System.out.println("Введите возраст:");
                        user.setAge(Integer.parseInt(scanner.nextLine()));
                        user.setCreatedAt(LocalDateTime.now());
                        userDaoImpl.create(user);
                        System.out.println("Пользователь создан");
                    }
                    catch(Exception e){
                        if(e == new NumberFormatException()){
                            System.out.println("Введите целочисленное значение!");
                        }
                    }
                    break;
                }
                case "2":{
                    System.out.println("Id | Name | Age | Email | Created_at");
                    for (User user : userDaoImpl.readAll()) {
                        System.out.println(user);
                    }
                    break;
                }
                case "3":{
                    System.out.println("Введите Id");
                    try{
                        User user = userDaoImpl.readById(Integer.parseInt(scanner.nextLine()));
                        if (user != null){
                            System.out.println("Id | Name | Age | Email | Created_at");
                            System.out.println(user);
                        }
                    }
                    catch(Exception e){
                        if(e == new NumberFormatException()){
                            System.out.println("Введите целочисленное значение!");
                        }
                    }
                    break;
                }
                case "4":{
                    
                    try{
                        System.out.println("Введите id:");
                        User user  = userDaoImpl.readById(Integer.parseInt(scanner.nextLine()));
                        if(user != null){
                            System.out.println("Введите имя:");
                            user.setName(scanner.nextLine());
                            System.out.println("Введите почту:");
                            user.setEmail(scanner.nextLine());
                            System.out.println("Введите возраст:");
                            user.setAge(Integer.parseInt(scanner.nextLine()));
                            userDaoImpl.update(user);
                            System.out.println("Пользователь обновлён");
                        }
                    }
                    catch(Exception e){
                        if(e == new NumberFormatException()){
                            System.out.println("Введите целочисленное значение!");
                        }
                    }
                    break;
                }
                case "5":{
                    try{
                        System.out.println("Введите id:");
                        User user = userDaoImpl.readById(Integer.parseInt(scanner.nextLine()));
                        if(user != null){
                            userDaoImpl.delete(user);
                            System.out.println("Пользователь удалён");
                        }
                    }
                    catch(Exception e){
                        if(e == new NumberFormatException()){
                            System.out.println("Введите целочисленное значение!");
                        }
                    }
                    break;
                }
                case "0":{
                    exitChecker = false;
                    break;
                }
            }
        }
        scanner.close();
    }
}
