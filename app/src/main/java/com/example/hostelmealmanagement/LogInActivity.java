package com.example.hostelmealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {
    EditText emailEditText,passwordEditText;
    Button signInButton;
    CheckBox rememberCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //view finding
        emailEditText=findViewById(R.id.signInEmailEditTextId);
        passwordEditText=findViewById(R.id.signInPasswordEditTextId);
        signInButton=findViewById(R.id.signInButtonId);
        rememberCheckBox=findViewById(R.id.rememberCheckBoxId);


    }
}