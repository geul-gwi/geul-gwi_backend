package com.posmosalimos.geulgwi.api.challenge.search.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ChallengeSearchDto {

    private Long seq;

    private String challengeContent;

    private Date regDate;

    private int like;
}
