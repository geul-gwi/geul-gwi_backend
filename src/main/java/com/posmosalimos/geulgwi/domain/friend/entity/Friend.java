package com.posmosalimos.geulgwi.domain.friend.entity;

import com.posmosalimos.geulgwi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friendList")
    private User toUser; //요청받은 사람

    private Long fromUser; //요청하는 사람

    private boolean approved;

    public String isApproved() {
        this.approved = true;
        return String.valueOf(approved);
    }

    @Builder
    public Friend(User toUser, Long fromUser, boolean approved) {
        this.toUser = toUser;
        this.fromUser = fromUser;
        this.approved = approved;
    }


}
