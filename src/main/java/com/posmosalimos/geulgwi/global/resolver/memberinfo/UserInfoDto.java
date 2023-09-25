package com.posmosalimos.geulgwi.global.resolver.memberinfo;

import com.posmosalimos.geulgwi.domain.geulgwi.entity.UploadFile;
import com.posmosalimos.geulgwi.domain.user.constant.Role;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class UserInfoDto {

    private Long userSeq;
    private String userId;
    private String nickname;
    private UploadFile profile;
    private Role role;
    private String comment;
    private String userPassword;
    private String tag1;
    private String tag2;
    private String tag3;
}
