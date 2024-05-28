package com.sbaldass.combo.controllers;

import com.sbaldass.combo.dto.RegisterUserDTO;
import com.sbaldass.combo.domain.User;
import com.sbaldass.combo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminController {
    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<User> createAdministrator(@RequestBody RegisterUserDTO registerUserDto) {
        User createdAdmin = userService.createAdministrator(registerUserDto);
        return ResponseEntity.ok(createdAdmin);
    }
}
