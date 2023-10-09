package com.posmosalimos.geulgwi.domain.tag.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.tag.constant.TagType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagSeq;
    private String backColor;
    private String fontColor;
    private String value;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private TagType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geulgwiSeq")
    private Geulgwi geulgwi;

    @Builder
    public Tag(String backColor, String fontColor, String value, TagType tagType) {
        this.backColor = backColor;
        this.fontColor = fontColor;
        this.value = value;
        this.type = tagType;
    }
}
