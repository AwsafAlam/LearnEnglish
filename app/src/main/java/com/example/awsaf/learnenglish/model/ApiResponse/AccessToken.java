package com.example.awsaf.learnenglish.model.ApiResponse;

import com.squareup.moshi.Json;

public class AccessToken {

    @Json(name = "token")
    String tokenType;
//    @Json(name = "expires_in")
//    int expiresIn;
//    @Json(name = "access_token")
//    String accessToken;
//    @Json(name = "refresh_token")
//    String refreshToken;


    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
