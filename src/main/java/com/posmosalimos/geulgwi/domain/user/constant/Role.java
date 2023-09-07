package com.posmosalimos.geulgwi.domain.user.constant;

public enum Role {
    ADMIN, COMMON;

    public static Role from (String role) {
        return Role.valueOf(role);
    }
}