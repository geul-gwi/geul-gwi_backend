package com.posmosalimos.geulgwi.api.geulgwi.search.dto;

import com.posmosalimos.geulgwi.domain.geulgwi.entity.GeulgwiTag;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import lombok.*;

import java.util.List;


@Data
@Builder
public class GeulgwiSrchDTO {

    @Getter @Setter
    @Builder @NoArgsConstructor @AllArgsConstructor
    public static class Response {
        private String geulgwiContent;
        private String regDate;
        private String file1;
        private String file2;
        private String file3;
        private int likeCount;
        private List<Tag> tags;
    }

    private String geulgwiContent;
    private String regDate;
    private int likeCount;

}
