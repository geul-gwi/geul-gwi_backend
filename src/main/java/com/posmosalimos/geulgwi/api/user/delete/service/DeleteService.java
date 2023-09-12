package com.posmosalimos.geulgwi.api.user.delete.service;

import com.posmosalimos.geulgwi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteService {
    private UserService userService;

    public void delete(Long seq, String password) {
        userService.delete(seq, password);
    }
}
