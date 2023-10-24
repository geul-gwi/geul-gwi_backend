package com.posmosalimos.geulgwi.domain.notice.entity;

import com.posmosalimos.geulgwi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toUserSeq")
    private User toUser; //알림받을 사람

    private Long fromUser; //알림보낸 사람

    private boolean checked;

    private Long friendSeq;

    private Long messageSeq;

    private Long geulgwiSeq;

    private Long geulgwiLikeSeq;

    private Long challengeLikeSeq;

    public void isChecked() {
        this.checked = true;
    }

    @Builder
    public Notice(User toUser, Long fromUser, boolean checked,
                  Long friendSeq, Long messageSeq, Long geulgwiSeq,
                  Long geulgwiLikeSeq, Long challengeLikeSeq) {
        this.toUser = toUser;
        this.fromUser = fromUser;
        this.checked = checked;
        this.friendSeq = friendSeq;
        this.messageSeq = messageSeq;
        this.geulgwiSeq = geulgwiSeq;
        this.geulgwiLikeSeq = geulgwiLikeSeq;
        this.challengeLikeSeq = challengeLikeSeq;
    }

}