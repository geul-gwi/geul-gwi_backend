package com.posmosalimos.geulgwi.domain.user.entity;

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
    @JoinColumn(name = "toUser")
    private User toUser; //요청받은 사람

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fromUser")
    private User fromUser; //요청하는 사람

    private String approved;

    private String subscriber;

    public void toggleApproval() { //기요청했던 사람 승인으로 변경
        this.approved = "T";
    }

    public void toggleSubscription() {
        if (this.subscriber.equals("T"))
            this.subscriber = "F";
        else //this.subscriber.equals("F")
            this.subscriber = "T";
    }

    @Builder
    public Friend(User toUser, User fromUser, String approved, String subscriber) {
        this.toUser = toUser;
        this.fromUser = fromUser;
        this.approved = approved;
        this.subscriber = subscriber;
    }

}
