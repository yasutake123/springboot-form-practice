package com.example.formpractice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.formpractice.form.UserForm;
import com.example.formpractice.service.UserService;
import com.example.formpractice.dto.ApiResponse;
import com.example.formpractice.dto.UserDto;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/users")
public class User {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> showForm() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<ApiResponse> submitForm(@Valid @RequestBody UserForm userForm, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(new ApiResponse("入力エラーが発生しました", errors));
        }

        UserDto dto = new UserDto();
        dto.setUserName(userForm.getUserName());
        dto.setAge(userForm.getAge());
        
        try {
            // 登録処理
            String message = userService.saveUser(dto);
            Map<String, Object> response = new HashMap<>();
            response.put("message", message);
            response.put("userList", userService.getAllUsers());
            return ResponseEntity.ok(new ApiResponse(message, response));

        } catch (IllegalArgumentException e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("errorMessage", e.getMessage());
            return ResponseEntity.badRequest().body(new ApiResponse("エラーが発生しました", errors));
        }
    }

    @DeleteMapping("/{name}")
    public List<UserDto> deleteUser(@PathVariable("name") String name) {
        userService.deleteUser(name);
        return userService.getAllUsers();
    }
}
