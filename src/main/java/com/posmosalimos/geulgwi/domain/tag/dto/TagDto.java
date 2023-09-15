package com.posmosalimos.geulgwi.domain.tag.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TagDto {

    @Data
    public static class Request{
        private String tagName;
        private String color;
    }
}
