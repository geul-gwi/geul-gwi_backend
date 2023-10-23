package com.posmosalimos.geulgwi.api.geulgwi.register.service;

import com.posmosalimos.geulgwi.api.geulgwi.register.dto.GeulgwiRegDTO;
import com.posmosalimos.geulgwi.domain.file.service.FileService;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.GeulgwiTag;
import com.posmosalimos.geulgwi.domain.geulgwi.repository.GeulgwiTagRepository;
import com.posmosalimos.geulgwi.domain.geulgwi.service.GeulgwiService;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.tag.service.TagService;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Transactional(readOnly = true)
@Service
@Slf4j
@RequiredArgsConstructor
public class GeulgwiRegService {

    private final GeulgwiService geulgwiService;
    private final TagService tagService;
    private final GeulgwiTagRepository geulgwiTagRepository;
    private final FileService fileService;
    private final UserService userService;

    @Transactional
    public void register(GeulgwiRegDTO geulgwiRegDTO, Long userSeq, List<MultipartFile> files) throws IOException {

        Geulgwi geulgwi = Geulgwi.builder()
                .content(geulgwiRegDTO.getGeulgwiContent())
                .user(userService.findBySeq(userSeq))
                .build();

        Geulgwi registerGeulgwi = geulgwiService.register(geulgwi); //글 등록

        if (files != null)
            fileService.storeGeulgwiFiles(registerGeulgwi, files); //파일 등록

            for (Long tagSeq : geulgwiRegDTO.getTagSeqs()) {
                Tag tag = tagService.findBySeq(tagSeq);
                GeulgwiTag geulgwiTag = GeulgwiTag.builder()
                        .geulgwi(geulgwi)
                        .tag(tag)
                        .build();

                geulgwiTagRepository.save(geulgwiTag);
            }
        }
}
