package com.posmosalimos.geulgwi.api.user.list.dto;

import com.posmosalimos.geulgwi.domain.geulgwi.entity.UploadFile;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class UserListDTO {

    private Long userSeq;
    private String userId;
    private String nickname;
    private UploadFile profile;
}
