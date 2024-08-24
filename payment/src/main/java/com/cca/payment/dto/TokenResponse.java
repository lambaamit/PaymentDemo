package com.cca.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String expiration;
    private String refreshToken;
    private String uid;

    public TokenResponse(String token, String expiration) {
        this.accessToken = token;
        this.expiration = expiration;
    }

    public TokenResponse(String token, String expiration, String refreshToken) {
        this.accessToken = token;
        this.expiration = expiration;
        this.refreshToken = refreshToken;
    }


}