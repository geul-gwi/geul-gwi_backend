package com.posmosalimos.geulgwi.domain.user.entity;

import com.posmosalimos.geulgwi.domain.challenge.entity.ChallengeUser;
import com.posmosalimos.geulgwi.domain.file.entity.UploadFile;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.notice.entity.Notice;
import com.posmosalimos.geulgwi.domain.user.constant.Role;
import com.posmosalimos.geulgwi.global.jwt.dto.JwtTokenDto;
import com.posmosalimos.geulgwi.global.util.DateTimeUtils;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String userId;

    @NotNull
    @Column(name = "userPassword")
    private String password;

    @NotNull
    @Column(name = "userAge")
    private int age;

    @NotNull
    @Column(name = "userGender")
    private String gender;

    @NotNull
    @Column(name = "userNickname")
    private String nickname;

    private String comment;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(length = 250)
    private String refreshToken;

    private LocalDateTime tokenExpirationTime;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ChallengeUser> challengePostList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Geulgwi> geulgwiPostList = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<UserTag> userTags = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UploadFile uploadFile;

    @OneToMany(mappedBy = "toUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Friend> friendList = new ArrayList<>();

    @OneToMany(mappedBy = "toUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notice> toUserNotificationList = new ArrayList<>();

    @OneToMany(mappedBy = "fromUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notice> fromUserNotificaionList = new ArrayList<>();

    @Builder
    public User(String userId, String password,
                String nickname, String gender,
                int age, Role role, String email) {
        this.userId = userId;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.nickname = nickname;
        this.role = role;
        this.email = email;
    }

    public void update(String password, String nickname, String comment) {
        this.password = password;
        this.nickname = nickname;
        this.comment = comment;
    }

    public void updateRefreshToken(JwtTokenDto jwtTokenDto) {
        this.refreshToken = jwtTokenDto.getRefreshToken();
        this.tokenExpirationTime = DateTimeUtils.convertToLocalDateTime(jwtTokenDto.getRefreshTokenExpireTime());
    }

    public void expireRefreshToken(LocalDateTime now) {
        this.tokenExpirationTime = now;
    }
}
