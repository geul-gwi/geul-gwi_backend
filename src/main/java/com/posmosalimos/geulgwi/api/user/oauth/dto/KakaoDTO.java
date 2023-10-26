package com.posmosalimos.geulgwi.api.user.oauth.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KakaoDTO {

    private String email;
    private String nickname;

}