package com.example.formpractice.service;

import java.util.List;

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
        if (userRepository.hasUser(user.getUserName())) {
            throw new IllegalArgumentException("ユーザー名は既に存在しています");
        }
        entity.setUserName(user.getUserName());
        entity.setAge(user.getAge());
        userRepository.save(entity);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
            .map(userEntity -> {
                UserDto dto = new UserDto();
                dto.setUserName(userEntity.getUserName());
                dto.setAge(userEntity.getAge());
                return dto;
            })
                .toList();
    }

    public void deleteUser(String name) {
        userRepository.deleteByUserName(name);
    }
}
