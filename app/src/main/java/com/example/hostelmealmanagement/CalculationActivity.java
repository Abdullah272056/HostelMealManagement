package com.example.hostelmealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hostelmealmanagement.calculation.GetAllCalculationDataResponse;
import com.example.hostelmealmanagement.retrofit.ApiInterface;
import com.example.hostelmealmanagement.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalculationActivity extends AppCompatActivity {
    String token;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        //receive user token
        token= getIntent().getStringExtra("token");

        //initialize apiInterface
        apiInterface = RetrofitClient.getRetrofit("http://hostel-meal-calc.herokuapp.com/").create(ApiInterface.class);

    }

    public  void  finalCalculation(){

        apiInterface.getFinalCalculation("Bearer "+token).enqueue(new Callback<GetAllCalculationDataResponse>() {
            @Override
            public void onResponse(Call<GetAllCalculationDataResponse> call, Response<GetAllCalculationDataResponse> response) {

            }

            @Override
            public void onFailure(Call<GetAllCalculationDataResponse> call, Throwable t) {

            }
        });
    }
}