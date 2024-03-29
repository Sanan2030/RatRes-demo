package com.Matazor.RatResDemo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRequest {
    private String name;
    private String email;
    private String username;
    private String gender;
    private String password;
}
