package com.example.formpractice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.formpractice.form.UserForm;
import com.example.formpractice.service.UserService;
import com.example.formpractice.dto.UserDto;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class User {
    @Autowired
    private UserService userService;

    @GetMapping("/user-form")
    public String showForm(Model model) {
        model.addAttribute("userForm", new UserForm());

        List<UserDto> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "user-index";
    }

    @PostMapping("/user-submit")
    public String submitForm(@Valid @ModelAttribute UserForm userForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user-index";
        }

        UserDto dto = new UserDto();
        dto.setUserName(userForm.getUserName());
        dto.setAge(userForm.getAge());
        try {
            String message = userService.saveUser(dto);
            model.addAttribute("message", message);
            model.addAttribute("age", userForm.getAge());
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user-index";
        }

        List<UserDto> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        
        return "user-index";
    }

    @GetMapping("/user-delete/{name}")
    public String deleteUser(@PathVariable("name") String name, Model model) {
        userService.deleteUser(name);
        List<UserDto> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "redirect:/user-form";
    }
    
    

}
