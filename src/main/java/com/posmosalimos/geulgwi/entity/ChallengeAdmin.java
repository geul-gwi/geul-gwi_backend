package com.posmosalimos.geulgwi.entity;

import jakarta.persistence.*;
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
}
