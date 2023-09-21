package com.posmosalimos.geulgwi.api.geulgwi.register.dto;

import com.posmosalimos.geulgwi.domain.geulgwi.entity.UploadFile;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class GeulgwiRegDTO {

    private String geulgwiContent;
    private Long userSeq;
    private List<UploadFile> files;
    private List<Tag> tags;
}
