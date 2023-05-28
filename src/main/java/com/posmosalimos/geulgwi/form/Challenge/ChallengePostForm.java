package com.posmosalimos.geulgwi.form.Challenge;


import lombok.Data;

@Data
public class ChallengePostForm {
//    private String userId;
    private String challengeContent;
    private int keyword_seq; // 프론트가 지정해서 넘겨줄 값...
}
