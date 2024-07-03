package com.sbaldass.combo.controllers;

import com.sbaldass.combo.domain.LoginResponse;
import com.sbaldass.combo.domain.User;
import com.sbaldass.combo.dto.LoginUserDTO;
import com.sbaldass.combo.dto.RegisterUserDTO;
import com.sbaldass.combo.services.AuthService;
import com.sbaldass.combo.services.JwtService;
import com.sbaldass.combo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private JwtService jwtService;

    private AuthService authenticationService;

    private UserService userService;

    @Autowired
    public AuthController(JwtService jwtService, AuthService authenticationService, UserService userService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDTO registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/admin")
    public ResponseEntity<User> createAdministrator(@RequestBody RegisterUserDTO registerUserDto) {
        User createdAdmin = userService.createAdministrator(registerUserDto);
        return ResponseEntity.ok(createdAdmin);
    }

    @PostMapping("/motoboy")
    public ResponseEntity<User> createMotoboy(@RequestBody RegisterUserDTO registerUserDto) {
        User createdMotoboy = userService.createMotoboy(registerUserDto);
        return ResponseEntity.ok(createdMotoboy);
    }
}

