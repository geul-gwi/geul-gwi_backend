package com.posmosalimos.geulgwi.api.geulgwi.delete.service;

import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.service.GeulgwiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GeulgwiDelService {

    private final GeulgwiService geulgwiService;

    @Transactional
    public void delete(Long geulgwiSeq) {

        Geulgwi findGeulgwi = geulgwiService.findBySeq(geulgwiSeq);
        geulgwiService.delete(findGeulgwi);
    }
}
