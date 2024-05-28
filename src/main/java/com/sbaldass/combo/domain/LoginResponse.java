package com.sbaldass.combo.domain;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private long expiresIn;
}
