package com.dosmart.mailService.controller;

import com.dosmart.mailService.dtos.BaseResponse;
import com.dosmart.mailService.dtos.EmailDetails;
import com.dosmart.mailService.services.PasswordMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/passcode")
public class PasswordMailController {
    @Autowired
    PasswordMailService passwordMailService;
    @PostMapping
    public BaseResponse<String> sendCode(@RequestBody EmailDetails emailDetails){
        String message = passwordMailService.sendEmail(emailDetails);
        if(message.contains("Successfully"))
        {
            return new BaseResponse<>("Message sent Successfully",HttpStatus.OK.value(),true,"",null);
        }
        return new BaseResponse<>(HttpStatus.FAILED_DEPENDENCY.getReasonPhrase(), HttpStatus.FAILED_DEPENDENCY.value(),false,"Unable to send mail",null);
    }
}
