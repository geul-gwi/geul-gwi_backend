package com.posmosalimos.geulgwi.domain.geulgwi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.posmosalimos.geulgwi.domain.like.entity.Likes;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String regDate;
    private String file1;
    private String file2;
    private String file3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userSeq")
    private User user;

    @OneToMany(mappedBy = "geulgwi")
    private List<GeulgwiTag> geulgwiTags = new ArrayList<>();

    @OneToMany(mappedBy = "geulgwi")
    private List<Likes> likes = new ArrayList<>();

    private int likeCount;

    @Builder
    public Geulgwi(String content, User user, List<String> files) {
        this.geulgwiContent = content;
        this.regDate = LocalDate.now().toString() + LocalTime.now();
        this.user = user;
        this.file1 = files.get(0);
        this.file2 = files.get(1);
        this.file3 = files.get(2);
    }

    public void update(String geulgwiContent, List<String> files) {
        this.geulgwiContent = geulgwiContent;
        this.regDate = LocalDate.now().toString() + LocalTime.now();
        this.file1 = files.get(0);
        this.file2 = files.get(1);
        this.file3 = files.get(2);
    }
}
