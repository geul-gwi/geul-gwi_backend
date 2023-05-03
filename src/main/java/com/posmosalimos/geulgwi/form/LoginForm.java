package com.posmosalimos.geulgwi.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginForm {
    @NotEmpty(message = "값을 입력해 주세요")
    private String userId;
    @NotEmpty(message = "값을 입력해 주세요")
    private String userPassword;
}