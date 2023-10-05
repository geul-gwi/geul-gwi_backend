package com.posmosalimos.geulgwi.api.geulgwi.search.dto;

import lombok.*;

@Data
@Builder
public class GeulgwiSrchDTO {

    @Getter @Setter
    @Builder @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private String geulgwiContent;
        private String regDate;
//        private Like likes;
    }

    private String geulgwiContent;
    private String regDate;
//    private Like likes;


}
