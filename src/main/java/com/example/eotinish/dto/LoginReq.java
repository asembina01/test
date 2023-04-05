package com.example.eotinish.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginReq {
    private String username;
    private String password;
}
