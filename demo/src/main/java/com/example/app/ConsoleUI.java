package com.example.app;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.example.entity.User;
import com.example.exeption.UserServiceImpl;
import com.example.config.HibernateUtil;
import com.example.dao.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleUI {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleUI.class);
    public static void mainMenu(){
        boolean exitChecker = true;
        Scanner scanner = new Scanner(System.in);
        UserServiceImpl dbPass = new UserServiceImpl(new UserDaoImpl(HibernateUtil.getSessionFactory()));

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
                        logger.info("Выбрана опция создания пользователя");
                        dbPass.create(user);
                        System.out.println("Пользователь создан");
                    }
                    catch(Exception e){
                        logger.error("Ошибка при создании пользователя", e);
                    }
                    break;
                }
                case "2":{
                    logger.info("выбрана опция вывода всех пользователей");
                    System.out.println("Id | Name | Age | Email | Created_at");
                    for (User user : dbPass.readAll()) {
                        System.out.println(user);
                    }
                    break;
                }
                case "3":{
                    logger.info("Выбрана опция поиска пользователя id");
                    System.out.println("Введите Id");
                    try{
                        User user = dbPass.readById(Integer.parseInt(scanner.nextLine()));
                        if (user != null){
                            System.out.println("Id | Name | Age | Email | Created_at");
                            System.out.println(user);
                        }
                    }
                    catch(Exception e){
                        logger.error("Ошибка при поиске пользователя по id", e);
                    }
                    break;
                }
                case "4":{
                    
                    try{
                        logger.info("Выбрана опция изменения пользователя");
                        System.out.println("Введите id:");
                        User user  = dbPass.readById(Integer.parseInt(scanner.nextLine()));
                        if(user != null){
                            System.out.println("Введите имя:");
                            user.setName(scanner.nextLine());
                            System.out.println("Введите почту:");
                            user.setEmail(scanner.nextLine());
                            System.out.println("Введите возраст:");
                            user.setAge(Integer.parseInt(scanner.nextLine()));
                            dbPass.update(user);
                            System.out.println("Пользователь обновлён");
                        }
                    }
                    catch(Exception e){
                        logger.error("Ошибка при изменении пользователя", e);
                    }
                    break;
                }
                case "5":{
                    try{
                        logger.info("Выбрана опция удаления пользователя");
                        System.out.println("Введите id:");
                        User user = dbPass.readById(Integer.parseInt(scanner.nextLine()));
                        if(user != null){
                            dbPass.delete(user);
                            System.out.println("Пользователь удалён");
                        }
                    }
                    catch(Exception e){
                        logger.error("Ошибка при удалении пользователя", e);
                    }
                    break;
                }
                case "0":{
                    logger.info("Работа программы завершена");
                    exitChecker = false;
                    break;
                }
                default:{
                    System.out.println("Неизвестная команда.");
                    break;
                }
            }
        }
        scanner.close();
    }
}
