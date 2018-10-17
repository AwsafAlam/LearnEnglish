package com.example.awsaf.learnenglish.model.ApiResponse;


import com.squareup.moshi.Json;

import java.util.List;


public class Response {

    @Json(name = "success")
    private Token successtoken;

    public Token getSuccesstoken() {
        return successtoken;
    }

    public void setSuccesstoken(Token successtoken) {
        this.successtoken = successtoken;
    }
    //    @SerializedName("nearby")
//    private int nearby;
//    @SerializedName("total_results")
//    private int totalResults;
//    @SerializedName("total_bikes")
//    private int totalBikes;


}