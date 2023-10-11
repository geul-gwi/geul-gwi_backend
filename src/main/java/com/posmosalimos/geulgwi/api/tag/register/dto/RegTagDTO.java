package com.posmosalimos.geulgwi.api.tag.register.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.posmosalimos.geulgwi.domain.tag.constant.TagType;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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
        private Long seq;
        private String backColor;
        private String fontColor;
        private String value;
        private String type;
    }
}
