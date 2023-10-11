package com.posmosalimos.geulgwi.api.tag.delete.service;

import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.tag.repository.TagRepository;
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
public class DelTagService {

    private final TagRepository tagRepository;

    @Transactional
    public void delete(Long tagSeq) {
        Tag tag = tagRepository.findById(tagSeq).orElseThrow(() -> new BusinessException(ErrorCode.TAG_DELETION_FAILED));
        tagRepository.delete(tag);
        }
}
