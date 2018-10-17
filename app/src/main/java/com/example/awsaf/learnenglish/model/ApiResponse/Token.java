package com.example.awsaf.learnenglish.model.ApiResponse;

import com.squareup.moshi.Json;

public class Token {

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Json(name = "token")
    private String token;

}
