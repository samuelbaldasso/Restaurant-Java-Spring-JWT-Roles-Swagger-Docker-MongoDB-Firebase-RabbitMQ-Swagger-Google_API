package com.sbaldass.combo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Email
    private String email;
    private String password;
    @ManyToMany
    private List<Role> roles = new ArrayList<>();
}
