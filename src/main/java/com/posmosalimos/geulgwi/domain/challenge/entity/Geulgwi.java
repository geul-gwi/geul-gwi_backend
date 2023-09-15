package com.posmosalimos.geulgwi.domain.challenge.entity;

import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Geulgwi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long geulgwiSeq;
    private String content;

    @OneToMany(mappedBy = "challengeUser", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();

    @Builder
    public Geulgwi(String content, List<Tag> tags) {
        this.content = content;
        this.tags = tags;
    }
}
