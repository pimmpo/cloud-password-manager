package com.pimmpo.passwordmanager.request;

import java.util.List;

import com.pimmpo.passwordmanager.model.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mavrin
 * @created 17.05.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long userId;
    private String username;
    private String email;
    private List<String> roles;
    
}
