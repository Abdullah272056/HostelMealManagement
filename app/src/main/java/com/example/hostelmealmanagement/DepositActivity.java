package com.example.hostelmealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.hostelmealmanagement.retrofit.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DepositActivity extends AppCompatActivity {
FloatingActionButton addDepositFloatingButton;
ApiInterface apiInterface;
String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        //receive user token
        token= getIntent().getStringExtra("token");
        //view finding
        addDepositFloatingButton=findViewById(R.id.addDepositFloatingButtonId);
        addDepositFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}