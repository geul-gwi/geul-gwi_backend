package com.posmosalimos.geulgwi.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UpdateForm {
    @NotEmpty(message = "값을 입력해 주세요")
    private String userPassword_current;
    @NotEmpty(message = "값을 입력해 주세요")
    private String userPassword_new;
    @NotEmpty(message = "값을 입력해 주세요")
    private String userName;
    @NotEmpty(message = "값을 입력해 주세요")
    private String userNickname;
    private String tag1;
    private String tag2;
    private String tag3;
    private String userProfile;
}
