package com.posmosalimos.geulgwi.api.tag.register.service;

import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.tag.repository.TagRepository;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RegTagService {

//    private final TagService tagService;
    private final TagRepository tagRepository;

    public void create(List<Tag> tags) {
        try {
            tagRepository.saveAll(tags);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.TAG_CREATION_FAILED);
        }
    }
}
