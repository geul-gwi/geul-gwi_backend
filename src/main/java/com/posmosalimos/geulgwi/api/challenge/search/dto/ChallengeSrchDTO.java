package com.posmosalimos.geulgwi.api.challenge.search.dto;

//import com.posmosalimos.geulgwi.domain.like.entity.Likes;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ChallengeSrchDTO {

    private Long seq;
    private String challengeContent;
    private String regDate;
//    private Likes likes;
}
