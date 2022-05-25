package com.pimmpo.passwordmanager.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mavrin
 * @created 25.05.2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateNoteRequest {
    
    private String login;
    private String password;
    private String  url;
    private String mark;
}
