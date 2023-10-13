package com.posmosalimos.geulgwi.api.user.update.dto;

import lombok.Data;

import java.util.List;

public class UpdateDTO {

    @Data
    public static class Request {
        private String password;
        private String nickname;
        private List<Long> userTagSeq;
        private String comment;
    }
}
