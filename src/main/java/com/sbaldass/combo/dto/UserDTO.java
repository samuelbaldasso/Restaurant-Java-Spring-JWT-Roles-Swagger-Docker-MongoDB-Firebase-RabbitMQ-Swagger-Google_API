package com.sbaldass.combo.dto;

import com.sbaldass.combo.domain.Role;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
        private Long id;
        private String username;
        private String email;
        private String password;
        private boolean isAdmin;
}
