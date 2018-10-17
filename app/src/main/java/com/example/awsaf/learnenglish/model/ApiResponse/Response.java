package com.example.awsaf.learnenglish.model.ApiResponse;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Response {

    @SerializedName("success")
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