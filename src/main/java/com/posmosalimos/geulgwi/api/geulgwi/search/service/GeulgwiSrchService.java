package com.posmosalimos.geulgwi.api.geulgwi.search.service;

import com.posmosalimos.geulgwi.api.geulgwi.search.dto.GeulgwiListDTO;
import com.posmosalimos.geulgwi.api.geulgwi.search.dto.GeulgwiSrchDTO;
import com.posmosalimos.geulgwi.api.tag.list.dto.TagDTO;
import com.posmosalimos.geulgwi.domain.file.entity.UploadFile;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.Geulgwi;
import com.posmosalimos.geulgwi.domain.geulgwi.entity.GeulgwiTag;
import com.posmosalimos.geulgwi.domain.geulgwi.service.GeulgwiService;
import com.posmosalimos.geulgwi.domain.like.service.LikeService;
import com.posmosalimos.geulgwi.domain.tag.entity.Tag;
import com.posmosalimos.geulgwi.domain.user.entity.User;
import com.posmosalimos.geulgwi.domain.user.service.UserService;
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
    private final LikeService likeService;
    private final UserService userService;

    public GeulgwiSrchDTO.Response search(Long geulgwiSeq, Long userSeq) {

        Geulgwi findGeulgwi = geulgwiService.findBySeq(geulgwiSeq);
        User findUser = userService.findBySeq(userSeq);

        Boolean isLiked = likeService.findByGeulgwi(findGeulgwi, findUser);

        List<GeulgwiTag> geulgwiTags = findGeulgwi.getGeulgwiTags();
        List<TagDTO> tags = new ArrayList<>();

        List<UploadFile> uploadFiles = findGeulgwi.getUploadFiles();
        List<String> storeFiles = new ArrayList<>();

        for (GeulgwiTag tag : geulgwiTags)
            tags.add(TagDTO.from(tag.getTag()));


        for (UploadFile uploadFile : uploadFiles)
            storeFiles.add(uploadFile.getStore());

        return GeulgwiSrchDTO.Response.builder()
                .geulgwiContent(findGeulgwi.getGeulgwiContent())
                .userSeq(findGeulgwi.getUser().getUserSeq())
                .regDate(findGeulgwi.getRegDate())
                .likeCount(findGeulgwi.getLikes().size())
                .files(storeFiles)
                .isLiked(isLiked)
                .tags(tags)
                .build();
    }

    public List<GeulgwiSrchDTO.Response> listByUserSeq(Long userSeq) {
        List<Geulgwi> list = geulgwiService.findByUserSeq(userSeq);
        List<GeulgwiSrchDTO.Response> dtos = new ArrayList<>();

        for (Geulgwi geulgwi : list) {
            List<GeulgwiTag> tags = geulgwi.getGeulgwiTags();
            List<TagDTO> tagDTOS = new ArrayList<>();
            List<UploadFile> files = geulgwi.getUploadFiles();
            List<String> storeFiles = new ArrayList<>();

            for (GeulgwiTag tag : tags)
                tagDTOS.add(TagDTO.from(tag.getTag()));

            for (UploadFile uploadFile : files)
                storeFiles.add(uploadFile.getStore());

            dtos.add(
                    GeulgwiSrchDTO.Response.builder()
                            .geulgwiContent(geulgwi.getGeulgwiContent())
                            .userSeq(geulgwi.getUser().getUserSeq())
                            .regDate(geulgwi.getRegDate())
                            .likeCount(geulgwi.getLikes().size())
                            .files(storeFiles)
                            .tags(tagDTOS)
                            .build());

        }
        return dtos;
    }

    public List<GeulgwiListDTO> listAll() {
        List<Geulgwi> list = geulgwiService.listAll();

        return list.stream()
                .map(geulgwi -> GeulgwiListDTO.builder()
                        .geulgwiSeq(geulgwi.getGeulgwiSeq())
                        .userSeq(geulgwi.getUser().getUserSeq())
                        .nickname(geulgwi.getUser().getNickname())
                        .geulgwiContent(geulgwi.getGeulgwiContent())
                        .regDate(geulgwi.getRegDate())
                        .build())
                .collect(Collectors.toList());

    }
}
