package com.dosmart.mailService.controller;

import com.dosmart.mailService.dtos.BaseResponse;
import com.dosmart.mailService.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    @PostMapping(value = "/forgot-password/{emailId}")
    public BaseResponse<String> forgetPassword(@PathVariable("emailId") String email)
    {
        try {
            if (!email.isEmpty()) {
                return adminService.sendCodeToMail(email);
            }
            return new BaseResponse<>("Invalid email", HttpStatus.NOT_ACCEPTABLE.value(), false, "No Admin Found", null);
        }
        catch (Exception exception)
        {
            return new BaseResponse<>(exception.toString(),HttpStatus.INTERNAL_SERVER_ERROR.value(), false,exception.getMessage(),null);
        }
    }
    @PostMapping(value = "/verify/{email}/{code}")
    public BaseResponse<String> verifyCode(@PathVariable("email") String email, @PathVariable("code") String code)
    {
       try {
          return adminService.verifyCode(email,code);
       }
       catch (Exception exception)
       {
           return new BaseResponse<>(exception.toString(),HttpStatus.INTERNAL_SERVER_ERROR.value(), false,exception.getMessage(),null);
       }
    }
}
