package com.posmosalimos.geulgwi.domain.notice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.posmosalimos.geulgwi.domain.notice.constant.Type;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toUserSeq")
    private User toUser; //알림 받을 사람

    private Long fromUser; //알림 보낸 사람

    private boolean checked;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String regDate;

    private Long friendSeq;

    private Long messageSeq;

    private Long geulgwiSeq;

    private Long geulgwiLikeSeq;

    private Long challengeSeq;

    private Long challengeLikeSeq;

    public void toggleChecking() {
        this.checked = true;
    }

    @Builder
    public Notice(Type type, User toUser, Long fromUser, boolean checked,
                  Long friendSeq, Long messageSeq, Long geulgwiSeq,
                  Long geulgwiLikeSeq, Long challengeSeq, Long challengeLikeSeq) {
        this.type = type;
        this.regDate = LocalDate.now().toString() + LocalTime.now();
        this.toUser = toUser;
        this.fromUser = fromUser;
        this.checked = checked;
        this.friendSeq = friendSeq;
        this.messageSeq = messageSeq;
        this.geulgwiSeq = geulgwiSeq;
        this.geulgwiLikeSeq = geulgwiLikeSeq;
        this.challengeSeq = challengeSeq;
        this.challengeLikeSeq = challengeLikeSeq;
    }

}