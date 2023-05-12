package com.posmosalimos.geulgwi.entity;

import com.posmosalimos.geulgwi.form.Post.WriteForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String userId;
    private String postTitle;
    private String postContent;
    private String file;
    private Date regDate;

    @Builder
    public Post(String userId, String postTitle, String postContent, String file, Date regDate) {
        this.userId = userId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.file = file;
        this.regDate = regDate;
    }
}