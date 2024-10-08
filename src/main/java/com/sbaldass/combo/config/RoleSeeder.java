package com.sbaldass.combo.config;

import com.sbaldass.combo.domain.Role;
import com.sbaldass.combo.domain.RoleName;
import com.sbaldass.combo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private RoleRepository roleRepository;

    @Autowired
    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    private void loadRoles() {
        RoleName[] roleNames = new RoleName[]{RoleName.ADMIN, RoleName.MOTOBOY, RoleName.CUSTOMER};
        Map<RoleName, String> roleDescriptionMap = Map.of(
                RoleName.MOTOBOY, "Default user role",
                RoleName.ADMIN, "Administrator role",
                RoleName.CUSTOMER, "Super admin role"
        );

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Role> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = new Role();

                roleToCreate.setName(roleName);
                roleToCreate
                        .setDescription(roleDescriptionMap.get(roleName));
                roleToCreate
                        .setCreatedAt(LocalDate.now());

                roleRepository.save(roleToCreate);
            });
        });
    }
}
