package com.posmosalimos.geulgwi.api.challenge.user.search.dto;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ChallengeSrchDTO {

    private Long seq;
    private String challengeContent;
    private String regDate;
    private int likeCount;
    private Boolean isLiked;

    public static ChallengeSrchDTO from(ChallengeUser challenge, Boolean isLiked){
        return ChallengeSrchDTO.builder()
                .seq(challenge.getChallengeUserSeq())
                .challengeContent(challenge.getChallengeContent())
                .regDate(challenge.getRegDate())
                .likeCount(challenge.getLikes().size())
                .isLiked(isLiked)
                .build();
    }
}
