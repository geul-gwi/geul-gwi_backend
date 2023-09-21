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

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class GeulgwiUdtService {

    private final GeulgwiService geulgwiService;

    @Transactional
    public void update(Long userSeq,
                       Long geulgwiSeq,
                       GeulgwiRegDTO geulgwiRegDTO) {

        Geulgwi geulgwiUser = geulgwiService.findBySeq(geulgwiSeq);

        if (!(geulgwiUser.getUser().getUserSeq().intValue() == userSeq))
            throw new AuthenticationException(ErrorCode.NOT_EQUAL_MEMBER);

        geulgwiUser.update(geulgwiRegDTO.getGeulgwiContent());

        geulgwiService.update(geulgwiUser);
    }
}
