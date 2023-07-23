package com.posmosalimos.geulgwi.global.error.exception;

import com.posmosalimos.geulgwi.global.error.ErrorCode;

public class AuthenticationException extends BusinessException{

    public AuthenticationException(ErrorCode errorCode){
        super(errorCode);
    }
}
