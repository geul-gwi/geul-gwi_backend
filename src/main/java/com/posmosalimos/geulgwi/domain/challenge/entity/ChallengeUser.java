package com.posmosalimos.geulgwi.domain.challenge.entity;

import com.posmosalimos.geulgwi.api.challenge.register.dto.ChallengeRegDTO;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class ChallengeUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long challengeUserSeq;
    private String challengeContent;
    private String regDate;
    private int likes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challengeAdminSeq")
    private ChallengeAdmin challengeAdmin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userSeq")
    private User user;

    @OneToMany(mappedBy = "challengeUser", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();

    @Builder
    public ChallengeUser(ChallengeRegDTO challengeRegDTO, ChallengeAdmin challengeAdmin, User user) {
        this.regDate = LocalDate.now().toString() + LocalTime.now();
        this.challengeContent = challengeRegDTO.getChallengeContent();
        this.challengeAdmin = challengeAdmin;
        this.user = user;
    }

    public void update(String challengeContent) {
        this.challengeContent = challengeContent;
        this.regDate = LocalDate.now().toString() + LocalTime.now();
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
