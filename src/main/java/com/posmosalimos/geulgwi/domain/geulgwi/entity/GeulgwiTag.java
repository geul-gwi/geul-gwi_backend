package com.posmosalimos.geulgwi.domain.geulgwi.entity;

import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class GeulgwiTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long geulgwiTagSeq;

    @ManyToOne
    @JoinColumn(name = "geulgwiSeq")
    Geulgwi geulgwi;

    @ManyToOne
    @JoinColumn(name = "tagSeq")
    Tag tag;

    @Builder
    public GeulgwiTag (Geulgwi geulgwi, Tag tag) {
        this.geulgwi = geulgwi;
        this.tag = tag;
    }

}
