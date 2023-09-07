package com.posmosalimos.geulgwi.domain.geulgwi.entity;

import com.posmosalimos.geulgwi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

public class Geulgwi {
    @Entity
    @Getter
    @NoArgsConstructor
    public class Post {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(updatable = false)
        private Long postSeq;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "userSeq")
        private User user;
        private String postTitle;
        private String postContent;
        private String file;
        private Date regDate;

//        @Builder
//        public Post(WriteForm form, Users user) {
//            this.user = user;
//            this.postTitle = form.getTitle();
//            this.postContent = form.getContent();
//            this.file = form.getFile();
//            this.regDate = new Date(System.currentTimeMillis());
//        }
//
//        public void update(WriteForm form) {
//            this.postTitle = form.getTitle();
//            this.postContent = form.getContent();
//            this.file = form.getFile();
//            this.regDate = new Date(System.currentTimeMillis());
//        }
    }
}
