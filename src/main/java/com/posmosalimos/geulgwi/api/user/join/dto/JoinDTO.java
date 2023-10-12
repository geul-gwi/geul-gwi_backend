package com.posmosalimos.geulgwi.api.user.join.dto;

import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Builder
public class JoinDTO {

    private String userId;
    private String userPassword;
    private String userNickname;
    private String userGender;
    private int userAge;
    private List<Long> userTagSeq;
}