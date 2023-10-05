package com.posmosalimos.geulgwi.api.challenge.search.dto;

//import com.posmosalimos.geulgwi.domain.like.entity.Like;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengeSrchDTO {

    private Long seq;
    private String challengeContent;
    private String regDate;
//    private Like likes;
}
