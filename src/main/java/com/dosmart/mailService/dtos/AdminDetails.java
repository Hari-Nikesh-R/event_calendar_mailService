package com.dosmart.mailService.dtos;

import lombok.Data;


@Data
public class AdminDetails {
    private String profilePicture;
    private String email;
    private String firstName;
    private String lastName;
    private String organization;
    private String phoneNumber;
    private String password;
    private String organizationalAddress;
    private boolean authority;
}
