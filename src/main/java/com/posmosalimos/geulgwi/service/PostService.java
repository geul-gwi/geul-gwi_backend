package com.posmosalimos.geulgwi.service;

import com.posmosalimos.geulgwi.entity.Post;
import com.posmosalimos.geulgwi.entity.Users;
import com.posmosalimos.geulgwi.form.Post.WriteForm;
import com.posmosalimos.geulgwi.repository.JpaPostRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final JpaPostRepository postRepository;

    //write
    public void write(WriteForm form, Users user) {

        Post post = Post.builder()
                        .form(form)
                        .user(user)
                        .build();

        postRepository.save(post);
    }

    //update
//    public void update(WriteForm form, Users user) {
//        Post post = user.getUserId()
//
//    }
}
