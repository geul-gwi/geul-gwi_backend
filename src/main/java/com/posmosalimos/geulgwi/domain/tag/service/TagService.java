package com.posmosalimos.geulgwi.domain.tag.service;

import com.posmosalimos.geulgwi.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

//    public Long createTag(CreateTagDTO.Request request) {
//
//        Tag tag = Tag.builder()
//                .backColor(request.getBackColor())
//                .fontColor(request.getFontColor())
//                .value(request.getValue())
//                .build();
//
//        tagRepository.save(tag);
//
//        return tag.getTagSeq();
//}

}
