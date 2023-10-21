package com.posmosalimos.geulgwi.api.geulgwi.search.dto;

import com.posmosalimos.geulgwi.api.tag.list.dto.TagDTO;
import lombok.*;

import java.util.List;


@Data
@Builder
public class GeulgwiSrchDTO {

    @Getter @Setter
    @Builder @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private String geulgwiContent;
        private Long userSeq;
        private String regDate;
        private List<String> files;
        private int likeCount;
        private Boolean isLiked;
        private List<TagDTO> tags;
    }

    private String geulgwiContent;
    private String regDate;
    private int likeCount;

}
