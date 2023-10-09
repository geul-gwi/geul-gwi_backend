package com.posmosalimos.geulgwi.domain.geulgwi.entity;

import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.Builder;

@Entity @Builder
public class GeulgwiTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagToGeulgwiSeq;

    @ManyToOne
    @JoinColumn(name = "geulgwiSeq")
    Geulgwi geulgwi;

    @ManyToOne
    @JoinColumn(name = "tagSeq")
    Tag tag;
}
