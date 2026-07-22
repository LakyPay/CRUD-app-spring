package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.exeption.UserServiceImpl;

public class UserServiceImplTest {
    private UserDao mockedUserDao;
    private UserServiceImpl serviceImpl;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("test name", "test email", 18, LocalDateTime.now());
        mockedUserDao = mock(UserDao.class);
        serviceImpl = new UserServiceImpl(mockedUserDao);
    }

    @Test
    public void createTest(){
        serviceImpl.create(user);
        verify(mockedUserDao, times(1)).create(user);
    }

    @Test
    public void readAllTest(){
        List<User> innerList = List.of(user);

        when(mockedUserDao.readAll())
            .thenReturn(innerList);
        
        List<User> resultList = serviceImpl.readAll();

        assertEquals(innerList, resultList);
        verify(mockedUserDao, times(1)).readAll();
    }

    @Test
    public void readByIdTest(){
        when(mockedUserDao.readById(1))
            .thenReturn(user);
        
        User resultUser = serviceImpl.readById(1);

        assertEquals(user, resultUser);
        verify(mockedUserDao, times(1)).readById(1);
    }

    @Test
    public void updateTest(){
        serviceImpl.update(user);
        verify(mockedUserDao, times(1)).update(user);
    }

    @Test
    public void deleteTest(){
        serviceImpl.delete(user);
        verify(mockedUserDao, times(1)).delete(user);
    }
}
