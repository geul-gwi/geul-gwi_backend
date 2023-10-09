package com.posmosalimos.geulgwi.domain.geulgwi.entity;

import com.posmosalimos.geulgwi.domain.file.entity.UploadFile;
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
    private String file1;
    private String file2;
    private String file3;

    @OneToMany(mappedBy = "geulgwi", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "likeSeq")
//    private Like likes;

    @Builder
    public Geulgwi(String content, List<Tag> tags, User user, List<String> files) {
        this.geulgwiContent = content;
        this.tags = tags;
        this.regDate = LocalDate.now().toString() + LocalTime.now();
        this.user = user;
        this.file1 = files.get(0);
        this.file2 = files.get(1);
        this.file3 = files.get(2);
    }

    public void update(String geulgwiContent, List<String> files) {
        this.geulgwiContent = geulgwiContent;
        this.regDate = LocalDate.now().toString() + LocalTime.now();
        this.tags = tags;
        this.file1 = files.get(0);
        this.file2 = files.get(1);
        this.file3 = files.get(2);
    }
}
