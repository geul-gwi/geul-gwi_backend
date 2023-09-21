package com.posmosalimos.geulgwi.domain.geulgwi.service;


import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.repository.GeulgwiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GeulgwiService {

    private final GeulgwiRepository geulgwiRepository;

    public void register(Geulgwi geulgwi) {
        geulgwiRepository.save(geulgwi);
    }
}
