package com.posmosalimos.geulgwi.service;

import com.posmosalimos.geulgwi.entity.Post;
import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.Post.WriteForm;
import com.posmosalimos.geulgwi.repository.JpaPostRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PostService {

    private final JpaPostRepository postRepository;

    //write
    @Transactional
    public void write(WriteForm form, Users user) {

        Post post = Post.builder()
                        .form(form)
                        .user(user)
                        .build();

        postRepository.save(post);
    }

    //update
    @Transactional
    public void update(WriteForm form, Long seq) {

        Post findPost = postRepository.findById(seq)
                .orElseThrow(() -> new NullPointerException("해당 게시물이 존재하지 않습니다."));

        findPost.update(form);
    }
}
