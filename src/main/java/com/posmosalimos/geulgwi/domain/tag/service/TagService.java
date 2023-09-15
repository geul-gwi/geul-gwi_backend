package com.posmosalimos.geulgwi.domain.tag.service;

import com.posmosalimos.geulgwi.domain.tag.dto.TagDto;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Long createTag(TagDto.Request request) {

        Tag tag = Tag.builder()
                .tagName(request.getTagName())
                .color(request.getColor())
                .build();

        tagRepository.save(tag);

        return tag.getTagSeq();
    }
}
