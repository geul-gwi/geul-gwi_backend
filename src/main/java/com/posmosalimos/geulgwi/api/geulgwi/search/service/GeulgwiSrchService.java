package com.posmosalimos.geulgwi.api.geulgwi.search.service;

import com.posmosalimos.geulgwi.api.geulgwi.search.dto.GeulgwiSrchDTO;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.service.GeulgwiService;
import com.posmosalimos.geulgwi.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class GeulgwiSrchService {

    private final GeulgwiService geulgwiService;

    public GeulgwiSrchDTO.Response search(Long seq) {
        Geulgwi dto = geulgwiService.findBySeq(seq);

        return GeulgwiSrchDTO.Response.builder()
                .geulgwiContent(dto.getGeulgwiContent())
                .regDate(dto.getRegDate())
                .file1(dto.getFile1())
                .file2(dto.getFile2())
                .file3(dto.getFile3())
                .likeCount(dto.getLikes().size())
                .build();
    }

}
