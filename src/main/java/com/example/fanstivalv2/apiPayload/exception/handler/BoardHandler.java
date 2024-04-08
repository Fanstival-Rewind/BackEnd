package com.example.fanstivalv2.apiPayload.exception.handler;

import com.example.fanstivalv2.apiPayload.code.BaseErrorCode;
import com.example.fanstivalv2.apiPayload.exception.GeneralException;

public class BoardHandler extends GeneralException {
    public BoardHandler(BaseErrorCode code){
        super(code);
    }
}
