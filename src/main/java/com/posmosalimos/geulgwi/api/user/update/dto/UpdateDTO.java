package com.posmosalimos.geulgwi.api.user.update.dto;

import lombok.Data;

import java.util.List;

public class UpdateDTO {

    @Data
    public static class Request {
        private String password;
        private String nickname;
        private List<String> tags;
        private String comment;
    }

//    @Data
//    public static class Response {
//        private String nickname;
//        private String comment;
//        private String profile;
//
//    }

}
