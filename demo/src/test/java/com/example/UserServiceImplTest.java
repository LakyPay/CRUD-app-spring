package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.entity.User;
import com.example.entity.UserDto;
import com.example.exeption.UserServiceImpl;
import com.example.repository.UserRepository;

public class UserServiceImplTest {
	private UserRepository userRepository; 
	private UserRepository mockedUserRepository; 
	private UserServiceImpl serviceImpl; 
	private UserDto userDto; 
	private User user;

    @BeforeEach
    public void setUp() {
    	userDto.setName("test name"); 
    	userDto.setEmail("test email"); 
    	userDto.setAge(15); 
    	userDto.setId(1); 
    	
    	user = userDto.toEntity(); 
    	
    	mockedUserRepository = mock(userRepository.getClass()); 
    	serviceImpl = new UserServiceImpl(mockedUserRepository);
    }

    @Test
    public void createTest(){
    	serviceImpl.create(userDto); 
    	verify(mockedUserRepository, times(1)).save(user);
    }

    @Test
    public void readAllTest(){
    	Iterable<User> innerList = List.of(user); 
    	when(mockedUserRepository.findAll()) 
    		.thenReturn(innerList); 
    	Iterable<User> resultList = serviceImpl.readAll(); 
    	assertEquals(innerList, resultList); 
    	verify(mockedUserRepository, times(1)).findAll();
    }

    @Test
    public void readByIdTest(){
    	Optional<User> timedOptional = Optional.of(user); 
    	when(mockedUserRepository.findById(1))
    		.thenReturn(timedOptional); 
    	Optional<User> resultUser = serviceImpl.readById(1); 
    	assertEquals(timedOptional, resultUser); 
    	verify(mockedUserRepository, times(1)).findById(1);
    }

    @Test
    public void updateTest(){
    	serviceImpl.update(userDto); 
    	verify(mockedUserRepository, times(1)).save(user);
    }

    @Test
    public void deleteTest(){
    	serviceImpl.delete(1); 
    	verify(mockedUserRepository, times(1)).deleteById(1);
    }
}
