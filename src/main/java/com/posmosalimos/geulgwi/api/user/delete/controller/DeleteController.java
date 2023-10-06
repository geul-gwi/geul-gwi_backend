package com.posmosalimos.geulgwi.api.user.delete.controller;

import com.posmosalimos.geulgwi.api.user.delete.dto.DeleteDTO;
import com.posmosalimos.geulgwi.api.user.delete.service.DeleteService;
import com.posmosalimos.geulgwi.global.jwt.service.TokenManager;
import com.posmosalimos.geulgwi.global.util.AuthorizationHeaderUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class DeleteController {

    private final TokenManager tokenManager;
    private final DeleteService deleteService;

    @DeleteMapping("/delete/{seq}")
    public ResponseEntity<Boolean> delete(@PathVariable Long seq, @RequestBody DeleteDTO deleteDTO,
                                          HttpServletRequest httpServletRequest) {

        String password = deleteDTO.getPassword();

        String authorization = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorization);

        String accessToken = authorization.split(" ")[1];

        tokenManager.validateToken(accessToken);

        deleteService.delete(seq, password);

        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/admin/delete/{seq}")
    public ResponseEntity<Boolean> delete(@PathVariable Long seq) {
        deleteService.delete(seq);

        return ResponseEntity.ok(true);
    }
}