package com.posmosalimos.geulgwi.api.challenge.user.search.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengeAdminDTO {

    private Long challengeSeq;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String comment;
    private String start;
    private String end;
}
