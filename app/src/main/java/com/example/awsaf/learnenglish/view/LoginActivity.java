package com.example.awsaf.learnenglish.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.awsaf.learnenglish.R;
import com.example.awsaf.learnenglish.Rest.ApiService;
import com.example.awsaf.learnenglish.Rest.RetrofitBuilder;
import com.example.awsaf.learnenglish.model.ApiResponse.ApiError;
import com.example.awsaf.learnenglish.model.ApiResponse.Response;
import com.example.awsaf.learnenglish.utils.NetworkUtlis.Utils;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
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

        final ProgressDialog mprogressDialog;
        mprogressDialog = new ProgressDialog(LoginActivity.this);
        mprogressDialog.setCancelable(false);
        mprogressDialog.setMessage("Logging In");
        mprogressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //mprogressDialog.setProgress(0);
        mprogressDialog.show();


        ApiService apiService = RetrofitBuilder.createService(ApiService.class);

        Call<Response> call = apiService.login(email , pass);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if(response.isSuccessful()){
                    Log.i("Awsaf_Debug" , "Success ->  "+response.body().getSuccesstoken().getToken());

                    Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                    if (mprogressDialog.isShowing())
                        mprogressDialog.dismiss();

                    startActivity(new Intent(LoginActivity.this , MainActivity.class));
                }
                else {
                    Toast.makeText(LoginActivity.this, "Wrong Username or pass", Toast.LENGTH_SHORT).show();

                    handleErrors(response.errorBody());
                }
                if (mprogressDialog.isShowing())
                    mprogressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                Log.i("Awsaf_Debug" , "Hello -> "+call.toString() +" "+call.clone().toString());
                if (mprogressDialog.isShowing())
                    mprogressDialog.dismiss();
            }
        });
    }


    private void handleErrors(ResponseBody response){

        ApiError apiError = Utils.converErrors(response);

        for(Map.Entry<String, List<String>> error : apiError.getErrors().entrySet()){

            if(error.getKey().equals("email")){
                email.setError(error.getValue().get(0));
            }
            if(error.getKey().equals("password")){
                password.setError(error.getValue().get(0));
            }
        }

    }

}
