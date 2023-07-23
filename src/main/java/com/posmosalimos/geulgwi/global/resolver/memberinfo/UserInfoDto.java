package com.posmosalimos.geulgwi.global.resolver.memberinfo;

import com.posmosalimos.geulgwi.entity.Role;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class UserInfoDto {

    private Long id;
    private String username;
    private String profile;
    private Role role;
}
