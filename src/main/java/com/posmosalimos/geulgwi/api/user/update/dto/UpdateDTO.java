package com.posmosalimos.geulgwi.api.user.update.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateDTO {
    private String userId;
    private String name;
    private String password;
    private String profile;
    private String nickname;
    private String tag1;
    private String tag2;
    private String tag3;

}
