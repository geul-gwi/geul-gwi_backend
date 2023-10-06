package com.posmosalimos.geulgwi.api.user.delete.service;

import com.posmosalimos.geulgwi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeleteService {
    private final UserService userService;

    @Transactional
    public void delete(Long seq, String password) {
        userService.delete(seq, password);
    }

    @Transactional
    public void delete(Long seq) {
        userService.delete(seq);
    }
}
