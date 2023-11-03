package com.posmosalimos.geulgwi.api.challenge.user.search.dto;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ChallengeSrchDTO {

    private Long challengeUserSeq;
    private Long userSeq;
    private String profile;
    private String nickname;
    private String challengeContent;
    private String regDate;
    private int likeCount;
    private Boolean isLiked;

    public static ChallengeSrchDTO from(ChallengeUser challenge, Boolean isLiked) {
        return ChallengeSrchDTO.builder()
                .challengeUserSeq(challenge.getChallengeUserSeq())
                .userSeq(challenge.getUser().getUserSeq())
                .profile(challenge.getUser().getUploadFile().getStore())
                .nickname(challenge.getUser().getNickname())
                .challengeContent(challenge.getChallengeContent())
                .regDate(challenge.getRegDate())
                .likeCount(challenge.getLikes().size())
                .isLiked(isLiked)
                .build();
    }
}
