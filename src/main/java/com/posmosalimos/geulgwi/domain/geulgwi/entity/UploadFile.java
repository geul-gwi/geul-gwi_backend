package com.posmosalimos.geulgwi.domain.geulgwi.entity;

import com.posmosalimos.geulgwi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class UploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileSeq;
    private String uploadFileName; //고객이 업로드한 파일명
    private String storeFileName; //서버 내부에서 관리하는 파일명(중복된 파일명일 때의 충돌 방지를 위해 서버용 파일명을 별도로 둠)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "geulgwiSeq")
    private Geulgwi geulgwi;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userSeq")
    private User user;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
