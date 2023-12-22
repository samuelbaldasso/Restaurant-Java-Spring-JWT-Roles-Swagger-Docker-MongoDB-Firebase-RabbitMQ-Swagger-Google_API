package com.sbaldass.combo.config;

import com.sbaldass.combo.domain.Role;
import com.sbaldass.combo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RolesDataInit implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");
    }

    private void createRoleIfNotFound(String name) {
        roleRepository.findByName(name).orElseGet(() -> {
            Role newRole = new Role();
            newRole.setName(name);
            return roleRepository.save(newRole);
        });
    }
}
