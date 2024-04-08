package com.example.fanstivalv2.apiPayload.exception.handler;

import com.example.fanstivalv2.apiPayload.code.BaseErrorCode;
import com.example.fanstivalv2.apiPayload.exception.GeneralException;

public class UserHandler extends GeneralException {

    public UserHandler(BaseErrorCode code){
        super(code);
    }
}
