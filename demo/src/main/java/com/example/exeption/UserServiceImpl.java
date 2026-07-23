package com.example.exeption;

import java.util.List;
import java.util.Optional;

import com.example.entity.User;
import com.example.entity.UserDto;
import com.example.repository.UserRepository;

public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository){ 
		this.userRepository = userRepository; 
	}

    @Override
    public void create(UserDto user){
    	userRepository.save(user.toEntity());
    }

    @Override
    public Iterable<User> readAll(){
    	return userRepository.findAll();
    }

    @Override
    public Optional<User> readById(int id){
    	return userRepository.findById(id);
    }

    @Override
    public void update(UserDto user){
    	userRepository.save(user.toEntity());
    }

    @Override
    public void delete(int id){
    	userRepository.deleteById(id);
    }
}
