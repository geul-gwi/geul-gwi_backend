package com.posmosalimos.geulgwi.domain.geulgwi.service;


import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.repository.GeulgwiRepository;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class GeulgwiService {

    private final GeulgwiRepository geulgwiRepository;

    public Geulgwi findBySeq(Long geulgwiSeq) {
        Geulgwi geulgwiUser = geulgwiRepository.findBySeq(geulgwiSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_EXISTS));

        return geulgwiUser;
    }

    @Transactional
    public void register(Geulgwi geulgwi) {
        geulgwiRepository.save(geulgwi);
    }

    @Transactional
    public void delete(Long seq) {
        geulgwiRepository.delete(seq);
    }

    @Transactional
    public void update(Geulgwi geulgwiUser) {
        geulgwiRepository.update(
                geulgwiUser.getGeulgwiSeq(),
                geulgwiUser.getGeulgwiContent(),
                geulgwiUser.getRegDate(),
                geulgwiUser.getFile1(),
                geulgwiUser.getFile2(),
                geulgwiUser.getFile3());
    }

//    @Transactional
//    public void likes(Long geulgwiSeq) {
//        geulgwiRepository.likes(geulgwiSeq);
//    }
}
