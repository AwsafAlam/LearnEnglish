package com.example.awsaf.learnenglish.Rest;

import com.example.awsaf.learnenglish.model.ApiResponse.Response;
import com.example.awsaf.learnenglish.model.ApiResponse.Token;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("register")
    @FormUrlEncoded
    Call<Response> register(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @POST("login")
    @FormUrlEncoded
    Call<Response> login(@Field("email") String username, @Field("password") String password);

    @POST("social_auth")
    @FormUrlEncoded
    Call<com.facebook.AccessToken> socialAuth(@Field("name") String name,
                                 @Field("email") String email,
                                 @Field("provider") String provider,
                                 @Field("provider_user_id") String providerUserId);

    @POST("refresh")
    @FormUrlEncoded
    Call<Token> refresh(@Field("refresh_token") String refreshToken);

//    @GET("posts")
//    Call<PostResponse> posts();

}
