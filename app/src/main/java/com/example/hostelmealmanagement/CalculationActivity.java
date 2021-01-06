package com.example.hostelmealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.hostelmealmanagement.calculation.GetAllCalculationData;
import com.example.hostelmealmanagement.calculation.GetAllCalculationDataResponse;
import com.example.hostelmealmanagement.retrofit.ApiInterface;
import com.example.hostelmealmanagement.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalculationActivity extends AppCompatActivity {
    String token;
    ApiInterface apiInterface;
    List<GetAllCalculationData> getAllCalculationDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        //receive user token
        token= getIntent().getStringExtra("token");

        //initialize apiInterface
        apiInterface = RetrofitClient.getRetrofit("http://hostel-meal-calc.herokuapp.com/").create(ApiInterface.class);
        finalCalculation();
    }

    public  void  finalCalculation(){

        apiInterface.getFinalCalculation("Bearer "+token).enqueue(new Callback<GetAllCalculationDataResponse>() {
            @Override
            public void onResponse(Call<GetAllCalculationDataResponse> call, Response<GetAllCalculationDataResponse> response) {
                if (response.code()==200){
                    getAllCalculationDataList=new ArrayList<>();
                    getAllCalculationDataList.addAll(response.body().getGetAllCalculationDataList());
                    Toast.makeText(CalculationActivity.this, "success"+String.valueOf(getAllCalculationDataList.size()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetAllCalculationDataResponse> call, Throwable t) {
                Toast.makeText(CalculationActivity.this, "fail", Toast.LENGTH_SHORT).show();

            }
        });
    }
}