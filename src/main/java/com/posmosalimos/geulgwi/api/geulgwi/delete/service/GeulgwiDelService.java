package com.posmosalimos.geulgwi.api.geulgwi.delete.service;

import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.service.GeulgwiService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
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
    public void delete(Long seq) {
        Geulgwi geulgwiUser = geulgwiService.findBySeq(seq);

        if (geulgwiUser.getUser().getUserSeq().intValue() != seq)
            throw new BusinessException(ErrorCode.MEMBER_NOT_EXISTS);

        geulgwiService.delete(seq);
    }
}
