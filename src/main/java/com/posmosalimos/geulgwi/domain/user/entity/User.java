package com.posmosalimos.geulgwi.domain.user.entity;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.user.constant.Role;
import com.posmosalimos.geulgwi.global.jwt.dto.JwtTokenDto;
import com.posmosalimos.geulgwi.global.util.DateTimeUtils;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String tag1;
    private String tag2;
    private String tag3;
    private String comment;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private String userProfile;
    @Column(length = 250)
    private String refreshToken;
    private LocalDateTime tokenExpirationTime;

    @OneToMany(mappedBy = "user")
    private List<ChallengeUser> challengePostList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Geulgwi> geulgwiPostList = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "likeSeq")
//    private Like likes;

    @Builder //join
    public User(String userId, String password,
                String nickname, String gender,
                int age, String tag1,
                String tag2, String tag3,
                Role role) {
        this.userId = userId;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.nickname = nickname;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.role = role;
    }

    public void update(String password, String nickname,
                       String tag1, String tag2,
                       String tag3, String profile,
                       String comment) {
        this.password = password;
        this.nickname = nickname;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.comment = comment;
        this.userProfile = profile;
    }

    public void updateRefreshToken(JwtTokenDto jwtTokenDto) {
        this.refreshToken = jwtTokenDto.getRefreshToken();
        this.tokenExpirationTime = DateTimeUtils.convertToLocalDateTime(jwtTokenDto.getRefreshTokenExpireTime());
    }

    public void expireRefreshToken(LocalDateTime now) {
        this.tokenExpirationTime = now;
    }
}
