package com.dosmart.mailService.dtos;

import lombok.Data;

import java.util.Map;

@Data
public class MessageCode {
    private String email;
    private Map<String,String> code;
}
