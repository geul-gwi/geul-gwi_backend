package com.posmosalimos.geulgwi.api.geulgwi.search.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeulgwiListDTO {

    private Long geulgwiSeq;
    private String nickname;
    private String geulgwiContent;
    private String regDate;
}
