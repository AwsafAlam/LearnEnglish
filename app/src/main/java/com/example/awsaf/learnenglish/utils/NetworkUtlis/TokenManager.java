package com.example.awsaf.learnenglish.utils.NetworkUtlis;

import android.content.SharedPreferences;

import com.example.awsaf.learnenglish.model.ApiResponse.Token;


public class TokenManager {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private static TokenManager INSTANCE = null;

    private TokenManager(SharedPreferences prefs){
        this.prefs = prefs;
        this.editor = prefs.edit();
    }

    public static synchronized TokenManager getInstance(SharedPreferences prefs){
        if(INSTANCE == null){
            INSTANCE = new TokenManager(prefs);
        }
        return INSTANCE;
    }

    public void saveToken(Token token){
        editor.putString("ACCESS_TOKEN", token.getToken()).commit();
    }

    public void deleteToken(){
        editor.remove("ACCESS_TOKEN").commit();
    }

    public String  getToken(){
        Token token = new Token();
        token.setToken(prefs.getString("ACCESS_TOKEN", null));
        return token.getToken();
    }



}
