package com.posmosalimos.geulgwi.api.tag.register.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RegTagDTO {

    private String backColor;
    private String fontColor;
    private String value;

    /*

[
     {
        "backColor": "asdf",
        "fontColor": "asdf",
        "value": "asdf"
     },
     {
        "backColor": "asdf",
        "fontColor": "asdf",
        "value": "asdf"
     }
]

     */

}
