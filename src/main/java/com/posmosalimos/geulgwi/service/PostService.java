package com.posmosalimos.geulgwi.service;

import com.posmosalimos.geulgwi.entity.Post;
import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.Post.WriteForm;
import com.posmosalimos.geulgwi.repository.JpaPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final JpaPostRepository postRepository;

    public void write(WriteForm form, Users user){
        Post post = new Post();
        post.setPostTitle(form.getTitle());
        post.setPostContent(form.getContent());
        post.setRegDate(new Date(System.currentTimeMillis()));
        post.setUserId(user.getUserId());
    }
}
