package com.posmosalimos.geulgwi.api.challenge.register.dto;


import lombok.Data;

@Data
public class ChallengeRegDTO {
    private String challengeContent;
    private Long keywordSeq; //ChallengeAdminSeq
    private Long userSeq; //User - UserSeq
}