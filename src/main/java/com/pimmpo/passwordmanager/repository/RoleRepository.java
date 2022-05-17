package com.pimmpo.passwordmanager.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pimmpo.passwordmanager.entity.Role;
import com.pimmpo.passwordmanager.model.UserRole;

/**
 * @author mavrin
 * @created 17.05.2022
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}
