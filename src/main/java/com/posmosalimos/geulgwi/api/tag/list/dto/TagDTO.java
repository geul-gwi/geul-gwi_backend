package com.posmosalimos.geulgwi.api.tag.list.dto;


import com.posmosalimos.geulgwi.domain.tag.constant.TagType;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagDTO {

    private Long tagSeq;
    private String backColor;
    private String fontColor;
    private String value;
    @Enumerated(EnumType.STRING)
    private TagType type;

    public static TagDTO from(Tag tag){
        return TagDTO.builder()
                        .tagSeq(tag.getTagSeq())
                        .backColor(tag.getBackColor())
                        .fontColor(tag.getFontColor())
                        .value(tag.getValue())
                        .type(tag.getType())
                        .build();
    }

}
