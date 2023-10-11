package com.posmosalimos.geulgwi.domain.tag.service;

import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.tag.repository.TagRepository;
import com.posmosalimos.geulgwi.global.error.ErrorCode;
import com.posmosalimos.geulgwi.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Tag findByValue(String value) {
        return tagRepository.findByValue(value);
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();


    }

    public Tag findBySeq(Long seq){

        return tagRepository.findById(seq)
                .orElseThrow(() -> new BusinessException(ErrorCode.TAG_NOT_FOUND));
    }

}
