package com.posmosalimos.geulgwi.api.user.join.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Builder
public class JoinDTO {

    private String userId;
    private String userPassword;
    private String userName;
    private String userNickname;
    private String userGender;
    private int userAge;
    private String tag1;
    private String tag2;
    private String tag3;
    private String userProfile;
}
