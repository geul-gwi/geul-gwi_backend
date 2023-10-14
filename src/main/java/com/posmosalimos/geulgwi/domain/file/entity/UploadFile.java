package com.posmosalimos.geulgwi.domain.file.entity;

import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class UploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileSeq;
    private String upload; //고객이 업로드한 파일명
    private String store; //서버 내부에서 관리하는 패스+파일명(중복된 파일명일 때의 충돌 방지를 위해 서버용 파일명을 별도로 둠)

    @OneToOne
    @JoinColumn(name = "userSeq", referencedColumnName = "userSeq")
    private User user;

    @ManyToOne
    @JoinColumn(name = "geulgwiSeq")
    private Geulgwi geulgwi;

    @Builder //글귀용
    public UploadFile(String upload, String store, Geulgwi geulgwi) {
        this.upload = upload;
        this.store = store;
        this.geulgwi = geulgwi;
    }

    @Builder //프로필사진용
    public UploadFile(String upload, String store, User user) {
        this.upload = upload;
        this.store = store;
        this.user = user;
    }

    public void update(String upload, String store) {
        this.upload = upload;
        this.store = store;
    }
}
