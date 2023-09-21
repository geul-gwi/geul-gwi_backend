package com.posmosalimos.geulgwi.api.geulgwi.search.service;

import com.posmosalimos.geulgwi.api.geulgwi.search.dto.GeulgwiSrchDTO;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.service.GeulgwiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class GeulgwiSrchService {

    private GeulgwiService geulgwiService;

    @Transactional
    public GeulgwiSrchDTO.Response search(Long seq) {
        Geulgwi dto = geulgwiService.findBySeq(seq);

        return GeulgwiSrchDTO.Response.builder()
                .geulgwiContent(dto.getGeulgwiContent())
                .regDate(dto.getRegDate())
                .likes(dto.getLikes())
                .build();
    }


}
