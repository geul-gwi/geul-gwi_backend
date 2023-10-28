package com.posmosalimos.geulgwi.api.challenge.admin.register.dto;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeAdmin;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ChallengeFormDTO {

    private String comment;
    private String start;
    private String end;
    private List<String> keyword;

    @Getter @Builder
    public static class Response {
        private Long challengeAdminSeq;
        private String comment;
        private String start;
        private String end;
        private List<String> keyword;
        private String status;


        public static ChallengeFormDTO.Response from(ChallengeAdmin challengeAdmin) {
            List<String> keyword = new ArrayList<>();

            keyword.add(challengeAdmin.getKeyword1());
            keyword.add(challengeAdmin.getKeyword2());
            keyword.add(challengeAdmin.getKeyword3());

            return Response.builder()
                    .challengeAdminSeq(challengeAdmin.getChallengeAdminSeq())
                    .comment(challengeAdmin.getComment())
                    .start(challengeAdmin.getStart())
                    .end(challengeAdmin.getEnd())
                    .keyword(keyword)
                    .status(challengeAdmin.getStatus().toString())
                    .build();
        }
    }
}
