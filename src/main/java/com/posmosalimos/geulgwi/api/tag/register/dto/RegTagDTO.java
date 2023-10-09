package com.posmosalimos.geulgwi.api.tag.register.dto;

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

    /*

[
     {
        "backColor": "asdf",
        "fontColor": "asdf",
        "value": "asdf"
     },
     {
        "backColor": "asdf",
        "fontColor": "asdf",
        "value": "asdf"
     }
]

     */

    public static RegTagDTO from(Tag tag){
        return RegTagDTO.builder()
                .backColor(tag.getBackColor())
                .fontColor(tag.getFontColor())
                .value(tag.getValue())
                .build();
    }

    public static Tag toUserEntity(RegTagDTO dto){
        return Tag.builder()
                .backColor(dto.getBackColor())
                .fontColor(dto.getFontColor())
                .value(dto.getValue())
                .tagType(TagType.USER_ADDED)
                .build();
    }

    public static Tag toDefaultEntity(RegTagDTO dto){
        return Tag.builder()
                .backColor(dto.getBackColor())
                .fontColor(dto.getFontColor())
                .value(dto.getValue())
                .tagType(TagType.DEFAULT)
                .build();
    }

    @Data @Builder
    public static class Response {
        private Long seq;
        private String backColor;
        private String fontColor;
        private String value;
    }
}
