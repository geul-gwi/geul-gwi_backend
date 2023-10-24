package com.posmosalimos.geulgwi.domain.geulgwi.service;


import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.repository.GeulgwiRepository;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class GeulgwiService {

    private final GeulgwiRepository geulgwiRepository;
    private final UserService userService;

    public Geulgwi findBySeq(Long geulgwiSeq) {
        return geulgwiRepository.findBySeq(geulgwiSeq)
                .orElseThrow(() -> new BusinessException(ErrorCode.GEULGWI_NOT_FOUND));
    }

    @Transactional
    public Geulgwi register(Geulgwi geulgwi) {
        return geulgwiRepository.save(geulgwi);
    }

    @Transactional
    public void delete(Geulgwi geulgwi) {
        geulgwiRepository.delete(geulgwi);
    }

    public List<Geulgwi> listAll() {
        return geulgwiRepository.findAll();
    }

    public List<Geulgwi> findByUserSeq(Long userSeq) {
        User findUser = userService.findBySeq(userSeq);
        return geulgwiRepository.findByUser(findUser);
    }
}
