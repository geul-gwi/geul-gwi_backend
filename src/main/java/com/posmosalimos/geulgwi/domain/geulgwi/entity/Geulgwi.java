package com.posmosalimos.geulgwi.domain.geulgwi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.posmosalimos.geulgwi.domain.file.entity.UploadFile;
import com.posmosalimos.geulgwi.domain.like.entity.Likes;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Geulgwi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long geulgwiSeq;
    private String geulgwiContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private String regDate;

    @ManyToOne
    @JoinColumn(name="userSeq")
    private User user;

    @OneToMany(mappedBy = "geulgwi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GeulgwiTag> geulgwiTags = new ArrayList<>();

    @OneToMany(mappedBy = "geulgwi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "geulgwi", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UploadFile> uploadFiles = new ArrayList<>();


    @Builder
    public Geulgwi(String content, User user, List<UploadFile> files) {
        this.geulgwiContent = content;
        this.regDate = String.valueOf(LocalDateTime.now());
        this.user = user;
        this.uploadFiles = files;

    }

    public void update(String geulgwiContent) {
        this.geulgwiContent = geulgwiContent;
    }
}
