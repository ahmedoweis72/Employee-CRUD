package com.demo.dto;

import com.demo.model.Role;

public class AuthResponse {
    private boolean success;
    private String message;
    private String username;

    public AuthResponse(boolean success, String message, String username, Role role, String token) {
        this.success = success;
        this.message = message;
        this.username = username;
        this.role = role;
        this.token = token;
    }

    private Role role;
    private String token;




    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getToken() { return token; }

    public Role getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }
}
