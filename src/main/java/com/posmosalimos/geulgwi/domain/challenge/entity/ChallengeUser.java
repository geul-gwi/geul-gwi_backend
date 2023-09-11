package com.posmosalimos.geulgwi.domain.challenge.entity;

import com.posmosalimos.geulgwi.api.challenge.register.dto.ChallengeRegDTO;
import com.posmosalimos.geulgwi.domain.user.entity.User;
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
    private String challengeContent;
    private Date regDate;
    private int like;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challengeAdminSeq")
    private ChallengeAdmin challengeAdmin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userSeq")
    private User user;

    @Builder
    public ChallengeUser(ChallengeRegDTO challengeRegDTO, ChallengeAdmin challengeAdmin, User user) {
        this.regDate = new Date(System.currentTimeMillis());
        this.challengeContent = challengeRegDTO.getChallengeContent();
        this.challengeAdmin = challengeAdmin;
        this.user = user;
    }

    public void update(ChallengeRegDTO challengeRegDTO) {
        this.challengeContent = challengeRegDTO.getChallengeContent();
        this.regDate = new Date(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "ChallengeUser{" +
                "challengeUserSeq=" + challengeUserSeq +
                ", challengeContent='" + challengeContent + '\'' +
                ", regDate=" + regDate +
                ", challengeAdmin=" + challengeAdmin +
                '}';
    }
}
