package com.posmosalimos.geulgwi.api.user.join.dto;

import lombok.Data;

@Data
public class EmailDTO {

    private String email;

    @Data
    public static class Valid {

        private String code;
    }

    @Data
    public static class Request {
        private String userId;
        private String email;
    }
}