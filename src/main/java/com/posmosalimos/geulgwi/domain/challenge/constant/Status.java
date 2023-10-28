package com.posmosalimos.geulgwi.domain.challenge.constant;


public enum Status {

    FINISHED, ONGOING, UPCOMING;

    public static Status from (String type) {
        return Status.valueOf(type);
    }
}
