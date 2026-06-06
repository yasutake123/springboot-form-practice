package com.example.formpractice.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class UserForm {
    private String userName;
    private Integer age;

    @NotBlank(message = "ユーザー名は必須です")
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @NotNull(message = "年齢は必須です")
    @Min(value = 0, message = "年齢は0以上でなければなりません")
    @Max(value = 150, message = "年齢は150以下でなければなりません")
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
