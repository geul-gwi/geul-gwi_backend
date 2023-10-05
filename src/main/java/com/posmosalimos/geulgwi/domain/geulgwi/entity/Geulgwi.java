package com.posmosalimos.geulgwi.domain.geulgwi.entity;

import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
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
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String regDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userSeq")
    private User user;

    @OneToMany(mappedBy = "geulgwi", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "geulgwi", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<UploadFile> uploadFiles = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "likeSeq")
//    private Like likes;

    @Builder
    public Geulgwi(String content, List<Tag> tags, User user) {
        this.geulgwiContent = content;
        this.tags = tags;
        this.regDate = LocalDate.now().toString() + LocalTime.now();
        this.user = user;
    }

    public void update(String geulgwiContent) {
        this.geulgwiContent = geulgwiContent;
        this.regDate = LocalDate.now().toString() + LocalTime.now();
    }
}
