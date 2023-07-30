package com.posmosalimos.geulgwi.global.resolver.memberinfo;

import com.posmosalimos.geulgwi.domain.user.constant.Role;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class UserInfoDto {

    private Long userSeq;
    private String userId;
    private String userName;
    private String nickname;
    private String profile;
    private Role role;
}
