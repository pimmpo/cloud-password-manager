package com.pimmpo.passwordmanager.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pimmpo.passwordmanager.entity.Role;
import com.pimmpo.passwordmanager.entity.User;
import com.pimmpo.passwordmanager.model.UserRole;
import com.pimmpo.passwordmanager.repository.RoleRepository;
import com.pimmpo.passwordmanager.repository.UserRepository;
import com.pimmpo.passwordmanager.request.JwtResponse;
import com.pimmpo.passwordmanager.request.LoginRequest;
import com.pimmpo.passwordmanager.request.SingupRequest;
import com.pimmpo.passwordmanager.response.MessageResponse;
import com.pimmpo.passwordmanager.service.UserDetailsImpl;
import com.pimmpo.passwordmanager.util.JwtUtils;

import lombok.RequiredArgsConstructor;

/**
 * @author mavrin
 * @created 17.05.2022
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    
    @PostMapping("/login")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(o -> o.getAuthority())
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(JwtResponse.builder()
                .token(jwt)
                .username(userDetails.getUsername())
                .userId(userDetails.getId())
                .email(userDetails.getEmail())
                .roles(roles)
                .build());
    }
    
    @PostMapping("/registry")
    public ResponseEntity<?> registryUser(@RequestBody SingupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is exist"));
        }

        Set<Role> roles = Set.of(roleRepository.findByName(UserRole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role didn't found")));
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User created!"));
    }
    
}
