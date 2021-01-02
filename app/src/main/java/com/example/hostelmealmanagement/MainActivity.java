package com.example.hostelmealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelmealmanagement.retrofit.ApiInterface;
import com.example.hostelmealmanagement.retrofit.RetrofitClient;
import com.example.hostelmealmanagement.retrofit.register.RegisterGetDataResponse;
import com.example.hostelmealmanagement.retrofit.register.RegisterSetDataResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
EditText memberNameEditText,emailEditText,hostelNameEditText,passwordEditText;
Button signUpButton;
TextView signInTextView;
Spinner memberTypeSpinner;
ApiInterface apiInterface;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // view finding
        memberNameEditText =findViewById(R.id.memberNameEditTextId);
        emailEditText =findViewById(R.id.emailEditTextId);
        hostelNameEditText =findViewById(R.id.hostelNameEditTextId);
        passwordEditText =findViewById(R.id.passwordEditTextId);

        signUpButton =findViewById(R.id.signUpButtonId);
        signInTextView =findViewById(R.id.signInTextViewId);
        memberTypeSpinner =findViewById(R.id.memberTypeSpinnerId);
        progressBar =findViewById(R.id.registrationProgressBarId);

        //initialize apiInterface
        apiInterface = RetrofitClient.getRetrofit("http://hostel-meal-calc.herokuapp.com/").create(ApiInterface.class);



            signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    register();
                }
            });

    }

    public void register(){
        String  memberName=memberNameEditText.getText().toString().trim();
        String  email=emailEditText.getText().toString().trim();
        String  hostelName=hostelNameEditText.getText().toString().trim();
        String  password=passwordEditText.getText().toString().trim();
        String memberType=memberTypeSpinner.getSelectedItem().toString();
        if (TextUtils.isEmpty(memberName)){
            memberNameEditText.setError("Enter your name");
            memberNameEditText.requestFocus();
            return;
        }
        if (memberName.length()<4){
            memberNameEditText.setError("name should be more than 4");
            memberNameEditText.requestFocus();
            return;
        }
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
        if (TextUtils.isEmpty(hostelName)){
            hostelNameEditText.setError("Enter hostel name");
            hostelNameEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)){
            passwordEditText.setError("Enter your password");
            passwordEditText.requestFocus();
            return;
        }

        if (password.length()<6){
            passwordEditText.setError("password should be more than 6");
            passwordEditText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        RegisterSetDataResponse registerSetDataResponse=new RegisterSetDataResponse(memberName,
                email,password,hostelName,memberType);
        apiInterface.registrationData(registerSetDataResponse).enqueue(new Callback<RegisterGetDataResponse>() {
            @Override
            public void onResponse(Call<RegisterGetDataResponse> call, Response<RegisterGetDataResponse> response) {
        try {
              if (response.code()==201){
                  Toast.makeText(MainActivity.this, "Create successful", Toast.LENGTH_SHORT).show();
              }else if(response.code()==400) {
                  emailEditText.setError("email already exist");
                  emailEditText.requestFocus();
                  Toast.makeText(MainActivity.this, "The email already exist", Toast.LENGTH_SHORT).show();
              }else if(response.code()==404) {
                  hostelNameEditText.setError("your hostel not created yet");
                  hostelNameEditText.requestFocus();
                  Toast.makeText(MainActivity.this, "Your hostel not created yet", Toast.LENGTH_SHORT).show();
              }
              else if(response.code()==401) {
                  hostelNameEditText.setError("Hostel name you entered already exist");
                  hostelNameEditText.requestFocus();
                  Toast.makeText(MainActivity.this, "Hostel name you entered already exist", Toast.LENGTH_SHORT).show();
                Log.e("err",response.errorBody().toString());
              }else {
                  Toast.makeText(MainActivity.this, "try again", Toast.LENGTH_SHORT).show();
              }
        }catch (Exception e){
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<RegisterGetDataResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                Log.e("ere",t.getMessage().toLowerCase());
                progressBar.setVisibility(View.INVISIBLE);

            }
        });

    }
}