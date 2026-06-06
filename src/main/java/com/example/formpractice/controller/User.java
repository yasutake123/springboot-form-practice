package com.example.formpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.formpractice.form.UserForm;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class User {

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
        String age = userForm.getAge();
        model.addAttribute("message", "こんにちは、" + userName + "さん！ 年齢: " + age);
        return "user-index";
    }
    
    

}
