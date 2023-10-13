package com.posmosalimos.geulgwi.api.geulgwi.register.dto;

import lombok.Data;

import java.util.List;

@Data
public class GeulgwiRegDTO {
    private String geulgwiContent;
    private Long userSeq;
    private List<Long> tagSeqs;
}
