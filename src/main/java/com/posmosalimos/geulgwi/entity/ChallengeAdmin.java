package com.posmosalimos.geulgwi.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ChallengeAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long challengeAdminSeq;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    @OneToMany(mappedBy = "challengeAdmin")
    private List<ChallengeUser> challengeUsers;

    @Builder
    public ChallengeAdmin(String[] keyword, Long keyword_seq) {
        this.challengeAdminSeq = keyword_seq;
        this.keyword1 = keyword[0];
        this.keyword2 = keyword[1];
        this.keyword3 = keyword[2];
    }
}
