package com.example.hostelmealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DepositActivity extends AppCompatActivity {
FloatingActionButton addDepositFloatingButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        //view finding
        addDepositFloatingButton=findViewById(R.id.addDepositFloatingButtonId);
        addDepositFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}