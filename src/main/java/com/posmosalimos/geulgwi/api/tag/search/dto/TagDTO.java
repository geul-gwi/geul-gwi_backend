package com.posmosalimos.geulgwi.api.tag.search.dto;


import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class TagDTO {

    private Long tagSeq;
    private String backColor;
    private String fontColor;
    private String value;
    private String type;

    public static TagDTO from(Tag tag){
        return TagDTO.builder()
                        .tagSeq(tag.getTagSeq())
                        .backColor(tag.getBackColor())
                        .fontColor(tag.getFontColor())
                        .value(tag.getValue())
                        .type(tag.getType().toString())
                        .build();
    }


    @Builder @Getter
    public static class Response {
        private Long tagSeq;
        private String backColor;
        private String fontColor;
        private String value;
        private Long count;

    }
}
