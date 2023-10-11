package com.posmosalimos.geulgwi.domain.user.entity;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.user.constant.Role;
import com.posmosalimos.geulgwi.global.jwt.dto.JwtTokenDto;
import com.posmosalimos.geulgwi.global.util.DateTimeUtils;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long userSeq;
    private String userId;
    @Column(name = "userPassword")
    private String password;
    @Column(name = "userAge")
    private int age;
    @Column(name = "userGender")
    private String gender;
    @Column(name = "userNickname")
    private String nickname;
    private String comment;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private String userProfile;
    @Column(length = 250)
    private String refreshToken;
    private LocalDateTime tokenExpirationTime;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ChallengeUser> challengePostList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Geulgwi> geulgwiPostList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<UserTag> userTags = new ArrayList<>();

    @Builder //join
    public User(String userId, String password,
                String nickname, String gender,
                int age, Role role, List<UserTag> userTags) {
        this.userId = userId;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.nickname = nickname;
        this.role = role;
        this.userTags = userTags;
    }

    public void update(String password, String nickname,
                       String profile, String comment,
                       List<UserTag> userTags) {
        this.password = password;
        this.nickname = nickname;
        this.userProfile = profile;
        this.comment = comment;
        this.userTags = userTags;
    }

    public void updateRefreshToken(JwtTokenDto jwtTokenDto) {
        this.refreshToken = jwtTokenDto.getRefreshToken();
        this.tokenExpirationTime = DateTimeUtils.convertToLocalDateTime(jwtTokenDto.getRefreshTokenExpireTime());
    }

    public void expireRefreshToken(LocalDateTime now) {
        this.tokenExpirationTime = now;
    }
}
