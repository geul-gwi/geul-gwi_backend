package com.posmosalimos.geulgwi.api.user.join.dto;

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
    private String userEmail;
    private int userAge;
    private List<Long> userTagSeq;
}