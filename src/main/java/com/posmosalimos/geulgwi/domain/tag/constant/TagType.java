package com.posmosalimos.geulgwi.domain.tag.constant;

public enum TagType {

    DEFAULT, USER_ADDED;

    public static TagType from (String tagType) {
        return TagType.valueOf(tagType);
    }
}
