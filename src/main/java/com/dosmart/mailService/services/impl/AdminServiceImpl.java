package com.dosmart.mailService.services.impl;

import com.dosmart.mailService.dtos.AdminDetails;
import com.dosmart.mailService.dtos.BaseResponse;
import com.dosmart.mailService.dtos.EmailDetails;
import com.dosmart.mailService.services.AdminService;
import com.dosmart.mailService.services.GenerateResetPassCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.dosmart.mailService.utils.Constants.AUTHENTICATION_URL;
import static com.dosmart.mailService.utils.Constants.MAIL_URL;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    GenerateResetPassCode generateResetPassCode;
    @Autowired
    RestTemplate restTemplate;

    private Map<String, String> generatedCode = new HashMap<>();
    @Override
    public BaseResponse<String> sendCodeToMail(String email) {
        AdminDetails details = new AdminDetails();
        details.setEmail(email);
        boolean validEmail = restTemplate.postForObject(AUTHENTICATION_URL + "/user/validate/email",details,Boolean.class);
        if(validEmail) {
            String response = "";
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(email);
            generatedCode.put(email, generateResetPassCode.generateCode());
            emailDetails.setCode(generatedCode);
            emailDetails.setMsgBody("Your code for Reset Password: " + emailDetails.getCode().get(email));
            emailDetails.setSubject("SECE Event Calendar - Reset Password");
            response = restTemplate.postForEntity(MAIL_URL + "/passcode", emailDetails, String.class).getBody();
            return new BaseResponse<>("", HttpStatus.OK.value(), true, "", response);
        }
        else{
            return new BaseResponse<>("User not found",HttpStatus.NO_CONTENT.value(), false,"Invalid Email",null);
        }
    }
    @Override
    public BaseResponse<String> verifyCode(String email,String code) {
            if (code != null) {
                if (code.equals(generatedCode.get(email))) {
                    return new BaseResponse<>("Code verified", HttpStatus.OK.value(), true, "", "Success");
                }
            }
        return new BaseResponse<>("Code not verified", HttpStatus.FORBIDDEN.value(), false, "", "Not Verified");
    }
    @Override
    public BaseResponse<Map<String, String>> getCode() {
        return new BaseResponse<>("Result fetched", HttpStatus.OK.value(),true, "", generatedCode);
    }
}
