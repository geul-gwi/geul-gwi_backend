package com.posmosalimos.geulgwi.entity;

public enum Role {
    ADMIN, USER;

    public static Role from (String role) {
        return Role.valueOf(role);
    }
}
