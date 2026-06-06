package com.example.formpractice.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.formpractice.entity.UserEntity;

@Repository
public class UserRepository {

    private List<UserEntity> users = new ArrayList<>();

    public void save(UserEntity user) {
        users.add(user);
    }

    public List<UserEntity> findAll() {
        return users;
    }

    public void deleteByUserName(String name) {
        users.removeIf(user -> user.getUserName().equals(name));
    }
    
}
