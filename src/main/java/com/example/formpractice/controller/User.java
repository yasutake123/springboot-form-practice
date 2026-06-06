package com.example.formpractice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.formpractice.form.UserForm;
import com.example.formpractice.service.UserService;
import com.example.formpractice.dto.UserDto;
import com.example.formpractice.entity.UserEntity;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class User {
    @Autowired
    private UserService userService;

    @GetMapping("/user-form")
    public String showForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "user-index";
    }

    @PostMapping("/user-submit")
    public String submitForm(@Valid @ModelAttribute UserForm userForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user-index";
        }
        
        String userName = userForm.getUserName();
        UserDto dto = new UserDto();
        dto.setUserName(userForm.getUserName());
        dto.setAge(userForm.getAge());
        userService.saveUser(dto);

        List<UserEntity> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        
        Integer age = userForm.getAge();
        String message = age < 20 
            ? "こんにちは、" + userName + "さん！（" + age + "歳）"
            : "こんにちは、" + userName + "さん！（" + age + "歳）※成人です";
        model.addAttribute("message", message);
        model.addAttribute("age", age);
        return "user-index";
    }
    
    

}
