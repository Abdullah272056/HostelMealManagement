package com.example.hostelmealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
EditText memberNameEditText,emailEditText,hostelNameEditText,passwordEditText;
Button signUpButton;
TextView signInTextView;
Spinner memberTypeSpinner;
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

        //token
        //eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVmZWIxODlmYTA5OGRlMDAxNzNhNjY3YiIsImlhdCI6MTYwOTMxNDkxNCwiZXhwIjoxNjExOTA2OTE0fQ.2N-4r1UEczEpqIXT6kYp17CzC8VoasBzgy_pyw9tklI
    }
}