package umc.hackathon.chagok.apiPayload.exception.handler;

import umc.hackathon.chagok.apiPayload.code.BaseErrorCode;
import umc.hackathon.chagok.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {

    public MemberHandler(BaseErrorCode errorCode){ super(errorCode); }
}
