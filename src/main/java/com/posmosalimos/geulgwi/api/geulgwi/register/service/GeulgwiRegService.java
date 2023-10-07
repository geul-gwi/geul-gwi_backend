package com.posmosalimos.geulgwi.api.geulgwi.register.service;

import com.posmosalimos.geulgwi.api.geulgwi.register.dto.GeulgwiRegDTO;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.service.GeulgwiService;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@Slf4j
@RequiredArgsConstructor
public class GeulgwiRegService {

    private final GeulgwiService geulgwiService;

    @Transactional
    public void register(GeulgwiRegDTO geulgwiRegDTO, User user, List<String> files) {

        Geulgwi geulgwi = Geulgwi.builder()
                .content(geulgwiRegDTO.getGeulgwiContent())
                .tags(geulgwiRegDTO.getTags())
                .user(user)
                .files(files)
                .build();

        geulgwiService.register(geulgwi);
    }

}
