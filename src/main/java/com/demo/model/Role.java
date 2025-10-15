package com.demo.model;

public enum Role {
    ADMIN,
    MANAGER,
    USER;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
