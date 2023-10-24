package com.posmosalimos.geulgwi.domain.notice.constant;

public enum Type {

    FRIEND, MESSAGE, GEULGWI, LIKE_GEULGWI, CHALLENGE, LIKE_CHALLENGE;


    public static Type from (String type) {
        return Type.valueOf(type);
    }
}
