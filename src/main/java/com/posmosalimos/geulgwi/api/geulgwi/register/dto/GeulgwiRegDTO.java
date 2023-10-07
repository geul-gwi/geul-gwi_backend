package com.posmosalimos.geulgwi.api.geulgwi.register.dto;

import com.posmosalimos.geulgwi.domain.file.entity.UploadFile;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import lombok.Data;

import java.util.List;

@Data
public class GeulgwiRegDTO {
    private String geulgwiContent;
    private Long userSeq;
    private List<Tag> tags;
}
