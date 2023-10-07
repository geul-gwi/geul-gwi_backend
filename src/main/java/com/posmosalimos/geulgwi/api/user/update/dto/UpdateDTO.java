package com.posmosalimos.geulgwi.api.user.update.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UpdateDTO {
    private String password;
    private String nickname;
    private List<String> tags;
    private String comment;
}
