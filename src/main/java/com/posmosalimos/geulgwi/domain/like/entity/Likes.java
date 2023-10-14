package com.posmosalimos.geulgwi.domain.like.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Entity
@Getter
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likeSeq")
    private Long likeSeq;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String likeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userSeq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geulgwiSeq")
    private Geulgwi geulgwi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challengeUserSeq")
    private ChallengeUser challengeUser;

    @Builder
    public Likes(User user, Geulgwi geulgwi, ChallengeUser challengeUser) {
        this.likeDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.user = user;
        this.geulgwi = geulgwi;
        this.challengeUser = challengeUser;
    }
}

