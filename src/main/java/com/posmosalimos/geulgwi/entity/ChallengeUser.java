package com.posmosalimos.geulgwi.entity;

import com.posmosalimos.geulgwi.form.Challenge.ChallengePostForm;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class ChallengeUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long challengeUserSeq;
    private String userId;
    private String challengeContent;
    private Date regDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challengeAdminSeq")
    private ChallengeAdmin challengeAdmin;

    @Builder
    public ChallengeUser(ChallengePostForm form, ChallengeAdmin challengeAdmin, Users user) {
        this.userId = user.getUserId();
        this.regDate = new Date(System.currentTimeMillis());
        this.challengeContent = form.getChallengeContent();
        this.challengeAdmin = challengeAdmin;
    }

    @Override
    public String toString() {
        return "ChallengeUser{" +
                "challengeUserSeq=" + challengeUserSeq +
                ", userId='" + userId + '\'' +
                ", challengeContent='" + challengeContent + '\'' +
                ", regDate=" + regDate +
                ", challengeAdmin=" + challengeAdmin +
                '}';
    }
}
