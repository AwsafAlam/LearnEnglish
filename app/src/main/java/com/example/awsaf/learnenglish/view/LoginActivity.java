package com.example.awsaf.learnenglish.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.awsaf.learnenglish.R;
import com.example.awsaf.learnenglish.Rest.ApiClient;
import com.example.awsaf.learnenglish.Rest.ApiInterface;
import com.example.awsaf.learnenglish.Rest.ApiService;
import com.example.awsaf.learnenglish.Rest.RetrofitBuilder;
import com.example.awsaf.learnenglish.model.ApiResponse.AccessToken;
import com.example.awsaf.learnenglish.model.ApiResponse.LocationInfo;
import com.example.awsaf.learnenglish.model.ApiResponse.Response;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private TextView email;
    private TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.button_signin);
        email = findViewById(R.id.editText_login_name);
        password = findViewById(R.id.pass);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(email.getText().toString() , password.getText().toString());
//                startActivity(new Intent(LoginActivity.this , MainActivity.class));
            }
        });
    }

    private void loginUser(String email, String pass) {

//        final ProgressDialog mprogressDialog;
//        mprogressDialog = new ProgressDialog(LoginActivity.this);
//        mprogressDialog.setCancelable(false);
//        mprogressDialog.setMessage("Finding Nearby Bikes");
//        mprogressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        //mprogressDialog.setProgress(0);
//        mprogressDialog.show();

        /*ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<Response> call = apiService.loginUser(email , pass); //Sending user data to API
        call.enqueue(new Callback<Response>() {

            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.i("Awsaf_Debug" , "Hello -> "+call.toString() +" "+call.clone().toString());
                Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                if (mprogressDialog.isShowing())
                        mprogressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.i("Awsaf_Debug" , "Hello -> "+call.toString() +" "+call.clone().toString());
                Toast.makeText(LoginActivity.this, "Failed to login "+call.toString(), Toast.LENGTH_SHORT).show();
                if (mprogressDialog.isShowing())
                    mprogressDialog.dismiss();
            }

        });*/

        ApiService apiService = RetrofitBuilder.createService(ApiService.class);

        Call<AccessToken> call = apiService.login(email , pass);
        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, retrofit2.Response<AccessToken> response) {
                Log.i("Awsaf_Debug" , "Success -> "+call.toString() +" ");

                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Succcess", Toast.LENGTH_SHORT).show();

                }
                else {

                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                Log.i("Awsaf_Debug" , "Hello -> "+call.toString() +" "+call.clone().toString());

            }
        });
    }
}
