package com.posmosalimos.geulgwi.domain.message.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageSeq;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 250)
    private String content;

    @Column(nullable = false)
    private String deletedBySender;

    @Column(nullable = false)
    private String deletedByReceiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senderSeq")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiverSeq")
    private User receiver;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private String regDate;

    public String deleteBySender() {
        this.deletedBySender = "Y";
        return deletedBySender;
    }

    public String deleteByReceiver() {
        this.deletedByReceiver = "Y";
        return deletedByReceiver;
    }


    @Builder
    public Message (String title, String content, User sender, User receiver, String deletedByReceiver, String deletedBySender) {
        this.regDate = String.valueOf(LocalDateTime.now());
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.deletedByReceiver = deletedByReceiver; //초기값 N
        this.deletedBySender = deletedBySender; //초기값 N
    }

}
