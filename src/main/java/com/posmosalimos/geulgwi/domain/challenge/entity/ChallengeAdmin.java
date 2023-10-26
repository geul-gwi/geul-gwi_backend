package com.posmosalimos.geulgwi.domain.challenge.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private String comment;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String end;

    @JsonIgnore
    @OneToMany(mappedBy = "challengeAdmin", fetch = FetchType.LAZY)
    private List<ChallengeUser> challengeUsers;

    @Builder
    public ChallengeAdmin(List<String> keyword, String start, String end, String comment) {
        this.keyword1 = keyword.get(0);
        this.keyword2 = keyword.get(1);
        this.keyword3 = keyword.get(2);
        this.start = start;
        this.end = end;
        this.comment = comment;
    }
}
