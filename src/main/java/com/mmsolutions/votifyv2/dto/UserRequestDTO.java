package com.mmsolutions.votifyv2.dto;

import lombok.Data;

@Data
public class UserRequestDTO {

    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

}
