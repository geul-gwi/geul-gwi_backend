package com.posmosalimos.geulgwi.form.User.email;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class EmailForm {

    @Email
    private String email;
}
