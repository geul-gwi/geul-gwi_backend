package com.posmosalimos.geulgwi.domain.tag.entity;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geulgwiSeq")
    private Geulgwi geulgwi;

    @Builder
    public Tag(String backColor, String fontColor, String value) {
        this.backColor = backColor;
        this.fontColor = fontColor;
        this.value = value;
    }
}
