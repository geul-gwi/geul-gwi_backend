package com.posmosalimos.geulgwi.api.user.search.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class UserListDTO {

    private Long userSeq;
    private String userId;
    private String nickname;
    private String profile;
}
