package com.example.formpractice.form;

import jakarta.validation.constraints.NotBlank;

public class UserForm {
    private String userName;
    private String age;

    @NotBlank(message = "ユーザー名は必須です")
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @NotBlank(message = "年齢は必須です")
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
}
