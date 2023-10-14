package com.posmosalimos.geulgwi.api.geulgwi.register.dto;

import lombok.Data;

import java.util.List;

@Data
public class GeulgwiRegDTO {
    private String geulgwiContent;
    private List<Long> tagSeqs;
}
