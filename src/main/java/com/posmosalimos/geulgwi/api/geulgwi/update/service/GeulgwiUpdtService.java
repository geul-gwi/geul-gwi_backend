package com.posmosalimos.geulgwi.api.geulgwi.update.service;


import com.posmosalimos.geulgwi.api.geulgwi.register.dto.GeulgwiRegDTO;
import com.posmosalimos.geulgwi.domain.file.service.FileService;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.GeulgwiTag;
import com.posmosalimos.geulgwi.domain.geulgwi.repository.GeulgwiTagRepository;
import com.posmosalimos.geulgwi.domain.geulgwi.service.GeulgwiService;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.tag.service.TagService;
import com.posmosalimos.geulgwi.domain.user.entity.UserTag;
import com.posmosalimos.geulgwi.domain.user.service.UserTagService;
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
    private final GeulgwiTagRepository geulgwiTagRepository;
    private final TagService tagService;

    @Transactional
    public void update(Long geulgwiSeq, GeulgwiRegDTO geulgwiRegDTO, List<MultipartFile> files) throws IOException {
        Geulgwi findGeulgwi = geulgwiService.findBySeq(geulgwiSeq);

        fileService.removeGeulgwiFiles(findGeulgwi); //기등록 파일 삭제

        if (files != null && !files.isEmpty())
            fileService.storeGeulgwiFiles(findGeulgwi, files); //파일 새로 추가

        geulgwiTagRepository.deleteByGeulgwi(findGeulgwi); //기등록 태그 전체 삭제

        for (Long tagSeq : geulgwiRegDTO.getTagSeqs()) { //태그 새로 추가
            Tag findTag = tagService.findBySeq(tagSeq);
            GeulgwiTag geulgwiTag = GeulgwiTag.builder()
                    .geulgwi(findGeulgwi)
                    .tag(findTag)
                    .build();

            geulgwiTagRepository.save(geulgwiTag);
        }

        findGeulgwi.update(geulgwiRegDTO.getGeulgwiContent()); //글 내용 업데이트
    }
}
