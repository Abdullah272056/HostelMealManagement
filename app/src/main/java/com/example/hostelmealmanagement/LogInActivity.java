package com.example.hostelmealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelmealmanagement.login.LogInGetDataResponse;
import com.example.hostelmealmanagement.login.LogInSetDataResponse;
import com.example.hostelmealmanagement.retrofit.ApiInterface;
import com.example.hostelmealmanagement.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {
    EditText emailEditText,passwordEditText;
    Button signInButton;
    CheckBox rememberCheckBox;
    TextView signUpTextView;
    ApiInterface apiInterface;
    SharePref sharePref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //view finding
        emailEditText = findViewById(R.id.signInEmailEditTextId);
        passwordEditText = findViewById(R.id.signInPasswordEditTextId);
        signInButton = findViewById(R.id.signInButtonId);
        rememberCheckBox = findViewById(R.id.rememberCheckBoxId);
        signUpTextView = findViewById(R.id.signUpTextViewId);
        //initialize apiInterface
        apiInterface = RetrofitClient.getRetrofit("http://hostel-meal-calc.herokuapp.com/").create(ApiInterface.class);

        // remember data retrieve and checking
        sharePref=new SharePref();
        String emailValue= sharePref.loadRememberEmail(LogInActivity.this);
        String passwordValue= sharePref.loadRememberPassword(LogInActivity.this);
        if (!emailValue.isEmpty() && !passwordValue.isEmpty()){
            emailEditText.setText(String.valueOf(emailValue));
            passwordEditText.setText(String.valueOf(passwordValue));
            login();
        }
        else {

            // Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
        }



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LogInActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }


    public  void  login(){
        final String email= emailEditText.getText().toString();
        final String password=passwordEditText.getText().toString();

        if (TextUtils.isEmpty(email)){
            emailEditText.setError("Enter your email");
            emailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Enter a valid  email address");
            emailEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)){
            passwordEditText.setError("Enter your password");
            passwordEditText.requestFocus();
            return;
        }
        LogInSetDataResponse logInSetDataResponse=new LogInSetDataResponse(email,password);
        apiInterface.logInData(logInSetDataResponse).enqueue(new Callback<LogInGetDataResponse>() {
            @Override
            public void onResponse(Call<LogInGetDataResponse> call, Response<LogInGetDataResponse> response){
                try {
                    if (response.code()==200){
                        sharePref=new SharePref();
                        if (rememberCheckBox.isChecked()){
                            sharePref.rememberData(LogInActivity.this,email,password);
                        }
                        Toast.makeText(LogInActivity.this, "success!", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(LogInActivity.this,HomeActivity.class);
                        intent.putExtra("token",response.body().getToken());
                        startActivity(intent);
                    }
                    else if (response.code()==401){
                        Toast.makeText(LogInActivity.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(LogInActivity.this, "Try again!", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(LogInActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LogInGetDataResponse> call, Throwable t) {
                Toast.makeText(LogInActivity.this, "failed! Try again", Toast.LENGTH_SHORT).show();

            }
        });


    }
}