package com.example.hostelmealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.hostelmealmanagement.retrofit.ApiInterface;
import com.example.hostelmealmanagement.retrofit.RetrofitClient;

public class HomeActivity extends AppCompatActivity {
    TextView totalDepositTextView,totalExpenseTextView,totalMealTextView,mealRateTextView;
    String token;

    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //receive user token
        token= getIntent().getStringExtra("token");
        //initialize apiInterface
        apiInterface = RetrofitClient.getRetrofit("http://hostel-meal-calc.herokuapp.com/").create(ApiInterface.class);

        //view finding
        totalDepositTextView=findViewById(R.id.totalDepositTextViewId);
        totalExpenseTextView=findViewById(R.id.totalExpenseTextViewId);
        totalMealTextView=findViewById(R.id.totalMealTextViewId);
        mealRateTextView=findViewById(R.id.mealRateTextViewId);
    }


}