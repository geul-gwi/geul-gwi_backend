package com.posmosalimos.geulgwi.api.user.update.dto;

import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class UpdateDTO {

    @Data
    public static class Request {
        private String password;
        private String nickname;
        private List<Tag> tags;
        private String comment;
    }

    @Builder
    public static class Response { //tag list
        private List<Tag> tags;
    }

}
