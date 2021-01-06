package com.example.hostelmealmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hostelmealmanagement.calculation.AllCalculationCustomAdapter;
import com.example.hostelmealmanagement.calculation.GetAllCalculationData;
import com.example.hostelmealmanagement.calculation.GetAllCalculationDataResponse;
import com.example.hostelmealmanagement.expense.GetAllExpenseCustomAdapter;
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
    RecyclerView calculationRecyclerView;
    AllCalculationCustomAdapter allCalculationCustomAdapter;
    ProgressBar calculationProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        //receive user token
        token= getIntent().getStringExtra("token");
        //view finding
        calculationRecyclerView=findViewById(R.id.calculationRecyclerViewId);
        calculationProgressBar=findViewById(R.id.calculationProgressBarId);
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

                    if (getAllCalculationDataList.size ()>0){
                        allCalculationCustomAdapter=new AllCalculationCustomAdapter(
                                CalculationActivity.this,token,getAllCalculationDataList);
                        calculationRecyclerView.setLayoutManager(new LinearLayoutManager(CalculationActivity.this));
                        calculationRecyclerView.setAdapter(allCalculationCustomAdapter);

                    }

                    Toast.makeText(CalculationActivity.this, "success"+String.valueOf(getAllCalculationDataList.size()), Toast.LENGTH_SHORT).show();
                }
                calculationProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<GetAllCalculationDataResponse> call, Throwable t) {
                Toast.makeText(CalculationActivity.this, "fail", Toast.LENGTH_SHORT).show();
                calculationProgressBar.setVisibility(View.INVISIBLE);

            }
        });
    }
}