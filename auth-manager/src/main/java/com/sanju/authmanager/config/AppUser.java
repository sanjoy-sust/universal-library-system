package com.sanju.authmanager.config;

import lombok.Data;

@Data
public class AppUser {
    private Integer id;
    private String username;
    private String password;
    private String role;

    public AppUser(Integer id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}