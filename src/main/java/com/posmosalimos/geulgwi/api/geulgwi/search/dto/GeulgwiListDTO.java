package com.posmosalimos.geulgwi.api.geulgwi.search.dto;

import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeulgwiListDTO {

    private Long geulgwiSeq;
    private Long userSeq;
    private String nickname;
    private String geulgwiContent;
    private String regDate;

    public static GeulgwiListDTO from(Geulgwi geulgwi) {
        return GeulgwiListDTO.builder()
                .geulgwiSeq(geulgwi.getGeulgwiSeq())
                .userSeq(geulgwi.getUser().getUserSeq())
                .nickname(geulgwi.getUser().getNickname())
                .geulgwiContent(geulgwi.getGeulgwiContent())
                .regDate(geulgwi.getRegDate())
                .build();
    }
}
