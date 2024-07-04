package com.sbaldass.combo.services;

import com.sbaldass.combo.config.BCryptPasswordEncoder;
import com.sbaldass.combo.domain.Role;
import com.sbaldass.combo.domain.RoleName;
import com.sbaldass.combo.domain.User;
import com.sbaldass.combo.dto.RegisterUserDTO;
import com.sbaldass.combo.repositories.RoleRepository;
import com.sbaldass.combo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> allUsers() {

        return new ArrayList<>(userRepository.findAll());
    }

    public User createAdministrator(RegisterUserDTO input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleName.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User();
        user
                .setFullName(input.getFullName());
        user
                .setEmail(input.getEmail());
        user
                .setPassword(passwordEncoder.passwordEncoder().encode(input.getPassword()));
        user
                .setRole(optionalRole.get());
        user
                .setCreatedAt(LocalDate.now());
        user
                .setPhoneNumber(input.getPhoneNumber());
        user
                .setPhotoUrl(input.getPhotoUrl());

        return userRepository.save(user);
    }

    public User createMotoboy(RegisterUserDTO input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleName.MOTOBOY);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User();
        user
                .setFullName(input.getFullName());
        user
                .setEmail(input.getEmail());
        user
                .setPassword(passwordEncoder.passwordEncoder().encode(input.getPassword()));
        user
                .setRole(optionalRole.get());
        user
                .setCreatedAt(LocalDate.now());
        user
                .setPhoneNumber(input.getPhoneNumber());
        user
                .setPhotoUrl(input.getPhotoUrl());

        return userRepository.save(user);
    }

    public User update(RegisterUserDTO input, String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user
                .setFullName(input.getFullName());
        user
                .setEmail(input.getEmail());
        user
                .setPassword(passwordEncoder.passwordEncoder().encode(input.getPassword()));
        user
                .setCreatedAt(LocalDate.now());
        user
                .setPhoneNumber(input.getPhoneNumber());
        user
                .setPhotoUrl(input.getPhotoUrl());

        return userRepository.save(user);
    }

    public void delete(String id){
        userRepository.deleteById(id);
    }
}
