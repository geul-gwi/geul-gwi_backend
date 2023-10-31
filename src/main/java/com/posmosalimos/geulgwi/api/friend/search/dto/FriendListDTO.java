package com.posmosalimos.geulgwi.api.friend.search.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendListDTO {

    private Long userSeq;
    private String userId;
    private String nickname;
    private String profile;
    private String isSubscribed;

}
