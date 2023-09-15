package com.posmosalimos.geulgwi.domain.challenge.entity;

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
    private String tagName;
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challengeUserSeq")
    private ChallengeUser challengeUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geulgwiSeq")
    private Geulgwi geulgwi;

    @Builder
    public Tag(String tagName, String color, ChallengeUser challengeUser, Geulgwi geulgwi) {
        this.tagName = tagName;
        this.color = color;
        this.challengeUser = challengeUser;
        this.geulgwi = geulgwi;
    }
}
