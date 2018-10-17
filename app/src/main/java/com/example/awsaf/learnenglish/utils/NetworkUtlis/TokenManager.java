package com.example.awsaf.learnenglish.utils.NetworkUtlis;

import android.content.SharedPreferences;

import com.example.awsaf.learnenglish.model.ApiResponse.AccessToken;


public class TokenManager {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private static TokenManager INSTANCE = null;

    private TokenManager(SharedPreferences prefs){
        this.prefs = prefs;
        this.editor = prefs.edit();
    }

    static synchronized TokenManager getInstance(SharedPreferences prefs){
        if(INSTANCE == null){
            INSTANCE = new TokenManager(prefs);
        }
        return INSTANCE;
    }

    public void saveToken(AccessToken token){
        editor.putString("ACCESS_TOKEN", token.getTokenType()).commit();
        editor.putString("REFRESH_TOKEN", token.getTokenType()).commit();
    }

    public void deleteToken(){
        editor.remove("ACCESS_TOKEN").commit();
        editor.remove("REFRESH_TOKEN").commit();
    }

    public AccessToken getToken(){
        AccessToken token = new AccessToken();
        token.setTokenType(prefs.getString("ACCESS_TOKEN", null));
        token.setTokenType(prefs.getString("REFRESH_TOKEN", null));
        return token;
    }



}
