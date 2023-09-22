package com.posmosalimos.geulgwi.domain.like.entity;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likeSeq")
    private Long likeSeq;

    @OneToMany(mappedBy = "likes", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "likes", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ChallengeUser> challengeUsers = new ArrayList<>();

}
