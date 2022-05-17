package com.pimmpo.passwordmanager.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pimmpo.passwordmanager.entity.User;

import lombok.AllArgsConstructor;

/**
 * @author mavrin
 * @created 17.05.2022
 */
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    
    public static UserDetailsImpl build(User user) {

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        
        return new UserDetailsImpl(user.getUserId(), user.getUsername(), user.getPassword(),
                user.getPassword(), authorities);
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return username;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}
