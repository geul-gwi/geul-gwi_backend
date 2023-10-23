package com.posmosalimos.geulgwi.api.friend.confirm.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendDTO {

    private Long toUser; //요청 받는 사람
    private Long fromUser; //요청 보내는 사람
}
