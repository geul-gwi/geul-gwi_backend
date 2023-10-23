package com.posmosalimos.geulgwi.domain.geulgwi.entity;

import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@NoArgsConstructor
public class GeulgwiTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long geulgwiTagSeq;

    @ManyToOne
    @JoinColumn(name = "geulgwiSeq")
    private Geulgwi geulgwi;

    @ManyToOne
    @JoinColumn(name = "tagSeq")
    private Tag tag;

    @Builder
    public GeulgwiTag (Geulgwi geulgwi, Tag tag) {
        this.geulgwi = geulgwi;
        this.tag = tag;
    }

}
