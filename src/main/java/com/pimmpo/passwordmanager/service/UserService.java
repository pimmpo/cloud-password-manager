package com.pimmpo.passwordmanager.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pimmpo.passwordmanager.entity.User;
import com.pimmpo.passwordmanager.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author mavrin
 * @created 25.05.2022
 */
@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    
    public User getUserByUsername(String username) {

        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username didn't find"));
    }
}
