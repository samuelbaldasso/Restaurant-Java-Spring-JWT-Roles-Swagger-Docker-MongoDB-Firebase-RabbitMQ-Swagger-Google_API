package com.sbaldass.combo.config;

import com.sbaldass.combo.domain.Role;
import com.sbaldass.combo.domain.RoleName;
import com.sbaldass.combo.domain.User;
import com.sbaldass.combo.dto.RegisterUserDTO;
import com.sbaldass.combo.repositories.RoleRepository;
import com.sbaldass.combo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminSeeder(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent contextRefreshedEvent) {
        this.createSuperAdministrator();
    }

    private void createSuperAdministrator() {
        RegisterUserDTO userDto = new RegisterUserDTO();
        userDto.setFullName("Super Admin");
        userDto.setEmail("super.admin@email.com");
        userDto.setPassword("123456");

        Optional<Role> optionalRole = roleRepository.findByName(RoleName.SUPER_ADMIN);
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = new User();
        user
                .setFullName(userDto.getFullName());
        user
                .setEmail(userDto.getEmail());
        user
                .setPassword(passwordEncoder.encode(userDto.getPassword()));
        user
                .setRole(optionalRole.get());

        userRepository.save(user);
    }
}