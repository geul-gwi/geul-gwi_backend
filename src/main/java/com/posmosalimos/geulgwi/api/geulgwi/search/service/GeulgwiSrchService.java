package com.posmosalimos.geulgwi.api.geulgwi.search.service;

import com.posmosalimos.geulgwi.api.geulgwi.search.dto.GeulgwiListDTO;
import com.posmosalimos.geulgwi.api.geulgwi.search.dto.GeulgwiSrchDTO;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.GeulgwiTag;
import com.posmosalimos.geulgwi.domain.geulgwi.service.GeulgwiService;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class GeulgwiSrchService {

    private final GeulgwiService geulgwiService;

    public GeulgwiSrchDTO.Response search(Long seq) {
        Geulgwi dto = geulgwiService.findBySeq(seq);
        List<GeulgwiTag> geulgwiTags = dto.getGeulgwiTags();
        List<Tag> tags = new ArrayList<>();

        for (GeulgwiTag tag : geulgwiTags)
            tags.add(tag.getTag());

        return GeulgwiSrchDTO.Response.builder()
                .geulgwiContent(dto.getGeulgwiContent())
                .regDate(dto.getRegDate())
                .likeCount(dto.getLikes().size())
                .tags(tags)
                .build();
    }

    public List<GeulgwiListDTO> list() {
        List<Geulgwi> list = geulgwiService.list();

        return list.stream()
                .map(geulgwi -> GeulgwiListDTO.builder()
                        .geulgwiSeq(geulgwi.getGeulgwiSeq())
                        .nickname(geulgwi.getUser().getNickname())
                        .geulgwiContent(geulgwi.getGeulgwiContent())
                        .regDate(geulgwi.getRegDate())
                        .build())
                .collect(Collectors.toList());

    }
}
