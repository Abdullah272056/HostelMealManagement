package com.example.hostelmealmanagement;

import androidx.appcompat.app.AppCompatActivity;

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

public class LogInActivity extends AppCompatActivity {
    EditText emailEditText,passwordEditText;
    Button signInButton;
    CheckBox rememberCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //view finding
        emailEditText = findViewById(R.id.signInEmailEditTextId);
        passwordEditText = findViewById(R.id.signInPasswordEditTextId);
        signInButton = findViewById(R.id.signInButtonId);
        rememberCheckBox = findViewById(R.id.rememberCheckBoxId);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }


    public  void  login(){

        String email= emailEditText.getText().toString();
        String password=passwordEditText.getText().toString();


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



    }
}