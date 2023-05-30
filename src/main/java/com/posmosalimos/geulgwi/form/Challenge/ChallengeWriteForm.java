package com.posmosalimos.geulgwi.form.Challenge;


import lombok.Data;

@Data
public class ChallengeWriteForm {
    private String challengeContent;
    private Long keyword_seq; // 프론트가 지정해서 넘겨줄 값...
}
