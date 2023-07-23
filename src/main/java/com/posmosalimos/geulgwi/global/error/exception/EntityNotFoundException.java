package com.posmosalimos.geulgwi.global.error.exception;

import com.posmosalimos.geulgwi.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException{

    public EntityNotFoundException(ErrorCode errorCode){
        super(errorCode);
    }
}

