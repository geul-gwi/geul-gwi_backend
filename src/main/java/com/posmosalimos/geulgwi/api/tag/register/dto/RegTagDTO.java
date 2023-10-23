package com.posmosalimos.geulgwi.api.tag.register.dto;

import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegTagDTO {

    private String backColor;
    private String fontColor;
    private String value;

    public static RegTagDTO from(Tag tag){
        return RegTagDTO.builder()
                .backColor(tag.getBackColor())
                .fontColor(tag.getFontColor())
                .value(tag.getValue())
                .build();
    }


    @Data @Builder
    public static class Response {
        private Long tagSeq;
        private String backColor;
        private String fontColor;
        private String value;
        private String type;
    }
}
