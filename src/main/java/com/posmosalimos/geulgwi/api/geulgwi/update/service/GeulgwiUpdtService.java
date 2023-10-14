package com.posmosalimos.geulgwi.api.geulgwi.update.service;


import com.posmosalimos.geulgwi.api.geulgwi.register.dto.GeulgwiRegDTO;
import com.posmosalimos.geulgwi.domain.file.service.FileService;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.service.GeulgwiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class GeulgwiUpdtService {

    private final GeulgwiService geulgwiService;
    private final FileService fileService;

    @Transactional
    public void update(Long geulgwiSeq, GeulgwiRegDTO geulgwiRegDTO) {
        Geulgwi findGeulgwi = geulgwiService.findBySeq(geulgwiSeq);
        findGeulgwi.update(geulgwiRegDTO.getGeulgwiContent()); //글 내용 업데이트
    }

    @Transactional
    public void uploadFiles(Long geulgwiSeq, List<MultipartFile> files) throws IOException {
        Geulgwi findGeulgwi = geulgwiService.findBySeq(geulgwiSeq);
        fileService.storeGeulgwiFiles(findGeulgwi, files);
    }

    @Transactional
    public void removeFiles(Long geulgwiSeq) {
        Geulgwi findGeulgwi = geulgwiService.findBySeq(geulgwiSeq);
        fileService.removeGeulgwiFiles(findGeulgwi);
    }


}
