package com.posmosalimos.geulgwi.entity;

import com.posmosalimos.geulgwi.form.Post.WriteForm;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpSession;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userSeq")
    private Users user;
    private String postTitle;
    private String postContent;
    private String file;
    private Date regDate;

    @Builder
    public Post(WriteForm form, Users user) {
        this.user = user;
        this.postTitle = form.getTitle();
        this.postContent = form.getContent();
        this.file = form.getFile();
        this.regDate = new Date(System.currentTimeMillis());
    }
}