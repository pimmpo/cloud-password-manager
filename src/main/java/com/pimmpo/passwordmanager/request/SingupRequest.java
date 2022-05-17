package com.pimmpo.passwordmanager.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mavrin
 * @created 17.05.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingupRequest {
    private String username;
    private String password;
    private String email;
}
