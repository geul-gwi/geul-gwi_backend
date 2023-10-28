package com.posmosalimos.geulgwi.domain.challenge.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.posmosalimos.geulgwi.api.challenge.user.register.dto.ChallengeRegDTO;
import com.posmosalimos.geulgwi.domain.like.entity.Likes;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private String regDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challengeAdminSeq")
    private ChallengeAdmin challengeAdmin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userSeq")
    private User user;

    @OneToMany(mappedBy = "challengeUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

    private int likeCount;


    @Builder
    public ChallengeUser(ChallengeRegDTO challengeRegDTO, ChallengeAdmin challengeAdmin, User user) {
        this.regDate = LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.challengeContent = challengeRegDTO.getChallengeContent();
        this.challengeAdmin = challengeAdmin;
        this.user = user;
    }

    public void update(String challengeContent) {
        this.challengeContent = challengeContent;
        this.regDate = LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
