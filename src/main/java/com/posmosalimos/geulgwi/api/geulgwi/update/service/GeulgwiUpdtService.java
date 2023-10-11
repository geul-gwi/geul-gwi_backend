package com.posmosalimos.geulgwi.api.geulgwi.update.service;


import com.posmosalimos.geulgwi.api.geulgwi.register.dto.GeulgwiRegDTO;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.service.GeulgwiService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.AuthenticationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class GeulgwiUpdtService {

    private final GeulgwiService geulgwiService;

    @Transactional
    public void update(Long geulgwiSeq,
                       GeulgwiRegDTO geulgwiRegDTO,
                       List<String> files) {

        Geulgwi geulgwiUser = geulgwiService.findBySeq(geulgwiSeq);

        geulgwiUser.update(geulgwiRegDTO.getGeulgwiContent(), files);
    }

}
