package com.example.awsaf.learnenglish.model.ApiResponse;

import com.google.gson.annotations.SerializedName;

public class Token {

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @SerializedName("token")
    private String token;

}
