package com.posmosalimos.geulgwi.api.challenge.admin.register.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChallengeFormDTO {

    private String comment;
    private String start;
    private String end;
    private List<String> keyword;

}
