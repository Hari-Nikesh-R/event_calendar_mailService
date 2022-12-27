package com.dosmart.mailService.services;

import com.dosmart.mailService.dtos.BaseResponse;

public interface AdminService {
    BaseResponse<String> sendCodeToMail(String email);
    BaseResponse<String> verifyCode(String email,String code);
}
