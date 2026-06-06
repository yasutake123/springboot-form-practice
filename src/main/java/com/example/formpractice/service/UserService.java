package com.example.formpractice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.formpractice.dto.UserDto;
import com.example.formpractice.entity.UserEntity;
import com.example.formpractice.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserDto user) {
        UserEntity entity = new UserEntity();
        entity.setUserName(user.getUserName());
        entity.setAge(user.getAge());
        userRepository.save(entity);
    }
}
