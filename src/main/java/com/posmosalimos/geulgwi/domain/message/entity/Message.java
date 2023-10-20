package com.posmosalimos.geulgwi.domain.message.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageSeq;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean deletedBySender;

    @Column(nullable = false)
    private boolean deletedByReceiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senderSeq")
    @OnDelete(action = OnDeleteAction.NO_ACTION) //계정 삭제 시 연쇄 삭제
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiverSeq")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User receiver;

    public void deleteBySender() {
        this.deletedBySender = true;
    }

    public void deleteByReceiver() {
        this.deletedByReceiver = true;
    }

    public boolean isDeleted() {
        return isDeletedBySender() && isDeletedByReceiver();
    }

    @Builder
    public Message (String title, String content, User sender, User reciever) {
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.receiver = reciever;
    }

}
