package com.pimmpo.passwordmanager.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.pimmpo.passwordmanager.entity.User;
import com.pimmpo.passwordmanager.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author mavrin
 * @created 17.05.2022
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User didn't found"));
        
        return UserDetailsImpl.build(user);
    }
}
