package com.sbaldass.combo.dto;

import lombok.Data;

@Data
public class RegisterUserDTO {
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String photoUrl;

}
