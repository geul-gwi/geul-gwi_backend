package com.posmosalimos.geulgwi.form.User.sms;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class PhoneNumberForm {
    @NotEmpty(message = "값을 입력해 주세요")
    private String phoneNumber;
}
