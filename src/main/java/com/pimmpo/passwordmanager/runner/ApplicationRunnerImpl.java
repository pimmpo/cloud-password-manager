package com.pimmpo.passwordmanager.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.pimmpo.passwordmanager.entity.Role;
import com.pimmpo.passwordmanager.model.UserRole;
import com.pimmpo.passwordmanager.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author mavrin
 * @created 25.05.2022
 */
@Component
@RequiredArgsConstructor
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final RoleRepository roleRepository;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role role = new Role();
        role.setName(UserRole.ROLE_USER);
        roleRepository.save(role);
    }
}
