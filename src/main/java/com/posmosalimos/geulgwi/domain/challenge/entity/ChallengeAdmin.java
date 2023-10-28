package com.posmosalimos.geulgwi.domain.challenge.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.posmosalimos.geulgwi.domain.challenge.constant.Status;
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
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private String start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private String end;

    @JsonIgnore
    @OneToMany(mappedBy = "challengeAdmin", fetch = FetchType.LAZY)
    private List<ChallengeUser> challengeUsers;

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void update(String comment, List<String> keyword, String start, String end, Status status) {
        this.keyword1 = keyword.get(0);
        this.keyword2 = keyword.get(1);
        this.keyword3 = keyword.get(2);
        this.start = start;
        this.end = end;
        this.comment = comment;
        this.status = status;
    }

    @Builder
    public ChallengeAdmin(List<String> keyword, String start, String end, String comment, Status status) {
        this.keyword1 = keyword.get(0);
        this.keyword2 = keyword.get(1);
        this.keyword3 = keyword.get(2);
        this.start = start;
        this.end = end;
        this.comment = comment;
        this.status = status;
    }
}
