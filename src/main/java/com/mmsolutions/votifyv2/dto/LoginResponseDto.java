package com.mmsolutions.votifyv2.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginResponseDto {
    private String token;

    public String getToken() {
        return token;
    }
}
