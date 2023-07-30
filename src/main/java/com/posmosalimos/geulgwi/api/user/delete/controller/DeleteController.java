package com.posmosalimos.geulgwi.api.user.delete.controller;

import com.posmosalimos.geulgwi.api.user.delete.service.DeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class DeleteController {

    private DeleteService deleteService;

    @PostMapping("/delete/{seq}")
    public ResponseEntity<String> delete(@PathVariable Long seq, @RequestBody String password) {
        deleteService.delete(seq, password);

        return ResponseEntity.ok("delete success");
    }

}