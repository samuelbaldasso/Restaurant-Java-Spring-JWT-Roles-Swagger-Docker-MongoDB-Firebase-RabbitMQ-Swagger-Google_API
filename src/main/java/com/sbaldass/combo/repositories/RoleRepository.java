package com.sbaldass.combo.repositories;

import com.sbaldass.combo.domain.Role;
import com.sbaldass.combo.domain.RoleName;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(RoleName name);
}
