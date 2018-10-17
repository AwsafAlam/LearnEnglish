package com.example.awsaf.learnenglish.model.ApiResponse;


import com.squareup.moshi.Json;


public class Response {

    @Json(name = "success")
    private Token successtoken;

    public Token getSuccesstoken() {
        return successtoken;
    }

    public void setSuccesstoken(Token successtoken) {
        this.successtoken = successtoken;
    }

}