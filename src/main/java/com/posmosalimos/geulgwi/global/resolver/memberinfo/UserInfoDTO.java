package com.posmosalimos.geulgwi.global.resolver.memberinfo;

import com.posmosalimos.geulgwi.api.tag.list.dto.TagDTO;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.user.constant.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
public class UserInfoDTO {

    private Long userSeq;
    private String userId;
    private String nickname;
    private String password;
    private String email;
    private String profile;
    private Role role;
    private String comment;
    private List<TagDTO> tags;

}
