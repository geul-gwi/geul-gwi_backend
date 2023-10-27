package com.posmosalimos.geulgwi.api.challenge.user.register.dto;


import lombok.Data;

@Data
public class ChallengeRegDTO {
    private String challengeContent;
    private Long challengeAdminSeq; //ChallengeAdminSeq
    private Long userSeq;
}