package com.example.awsaf.learnenglish.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.awsaf.learnenglish.R;
import com.example.awsaf.learnenglish.Rest.ApiService;
import com.example.awsaf.learnenglish.Rest.RetrofitBuilder;
import com.example.awsaf.learnenglish.model.ApiResponse.ApiError;
import com.example.awsaf.learnenglish.model.ApiResponse.Response;
import com.example.awsaf.learnenglish.utils.NetworkUtlis.TokenManager;
import com.example.awsaf.learnenglish.utils.NetworkUtlis.Utils;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText email;
    private EditText password;
    private TextView signup1;
    private TextView signup2;

    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.button_signin);
        email = findViewById(R.id.editText_login_name);
        password = findViewById(R.id.pass);

        signup1 = findViewById(R.id.signup_button);
        signup2 = findViewById(R.id.textView_signup);

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(email.getText().toString() , password.getText().toString());
            }
        });

        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this , RegisterActivity.class));
            }
        });
        signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this , RegisterActivity.class));
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
                    tokenManager.saveToken(response.body().getSuccesstoken());
                    Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                    if (mprogressDialog.isShowing())
                        mprogressDialog.dismiss();

                    startActivity(new Intent(LoginActivity.this , MainActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Wrong Username or pass", Toast.LENGTH_SHORT).show();
                    if (mprogressDialog.isShowing())
                        mprogressDialog.dismiss();
//                    handleErrors(response.errorBody());
                }

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
