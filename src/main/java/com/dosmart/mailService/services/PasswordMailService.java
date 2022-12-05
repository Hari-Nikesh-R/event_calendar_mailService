package com.dosmart.mailService.services;

import com.dosmart.mailService.dtos.EmailDetails;

public interface PasswordMailService {
    String sendEmail(EmailDetails emailDetails);

}

