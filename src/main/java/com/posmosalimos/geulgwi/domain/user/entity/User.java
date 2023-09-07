package com.posmosalimos.geulgwi.domain.user.entity;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.user.constant.Role;
import com.posmosalimos.geulgwi.global.jwt.dto.JwtTokenDto;
import com.posmosalimos.geulgwi.global.util.DateTimeUtils;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long userSeq;
    private String userId;
    @Column(name = "userPassword")
    private String password;
    @Column(name = "userName")
    private String userName;
    @Column(name = "userAge")
    private int age;
    @Column(name = "userGender")
    private String gender;
    @Column(name = "userNickname")
    private String nickname;
    private String tag1;
    private String tag2;
    private String tag3;
    @Column(name = "userProfile")
    private String profile;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Column(length = 250)
    private String refreshToken;
    private LocalDateTime tokenExpirationTime;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ChallengeUser> challengePostList;

    @Builder //join
    public User(String userId, String password,
                String userName, String nickname,
                String gender, int age,
                String tag1, String tag2,
                String tag3, String profile,
                Role role
                ) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.age = age;
        this.gender = gender;
        this.nickname = nickname;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.profile = profile;
        this.role = role;
    }

    public void update(String password, String userName, String nickname, String tag1, String tag2, String tag3, String profile) {
        this.password = password;
        this.userName = userName;
        this.nickname = nickname;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.profile = profile;
    }

    public void updateRefreshToken(JwtTokenDto jwtTokenDto) {
        this.refreshToken = jwtTokenDto.getRefreshToken();
        this.tokenExpirationTime = DateTimeUtils.convertToLocalDateTime(jwtTokenDto.getRefreshTokenExpireTime());
    }

    public void expireRefreshToken(LocalDateTime now) {
        this.tokenExpirationTime = now;
    }
}