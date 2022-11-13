package com.cts.fse.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private String roles;
}
