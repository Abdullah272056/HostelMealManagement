package com.example.hostelmealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView totalDepositTextView,totalExpenseTextView,totalMealTextView,mealRateTextView;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //receive user token
        token= getIntent().getStringExtra("token");
        
        //view finding
        totalDepositTextView=findViewById(R.id.totalDepositTextViewId);
        totalExpenseTextView=findViewById(R.id.totalExpenseTextViewId);
        totalMealTextView=findViewById(R.id.totalMealTextViewId);
        mealRateTextView=findViewById(R.id.mealRateTextViewId);
    }
}