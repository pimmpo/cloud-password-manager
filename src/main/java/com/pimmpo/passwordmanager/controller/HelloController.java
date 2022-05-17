package com.pimmpo.passwordmanager.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mavrin
 * @created 17.05.2022
 */
@RestController
@RequestMapping("/api/test")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "KY";
    }
    
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/hello2")
    public String hello2() {
        return "KY2";
    }
}
