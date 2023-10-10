package com.posmosalimos.geulgwi.domain.like.entity;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likeSeq")
    private Long likeSeq;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime likeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userSeq")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geulgwiSeq")
    private Geulgwi geulgwi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challengeUserSeq")
    private ChallengeUser challengeUser;
}

