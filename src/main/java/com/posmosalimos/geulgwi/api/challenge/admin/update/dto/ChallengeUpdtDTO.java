package com.posmosalimos.geulgwi.api.challenge.admin.update.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChallengeUpdtDTO {

    private Long challengeAdminSeq;
    private String comment;
    private String start;
    private String end;
    private List<String> keyword;
    private String status;
}
