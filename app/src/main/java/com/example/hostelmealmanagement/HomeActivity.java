package com.example.hostelmealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelmealmanagement.deposit.TotalDepositAmountDataResponse;
import com.example.hostelmealmanagement.expense.TotalExpenseAmountDataResponse;
import com.example.hostelmealmanagement.retrofit.ApiInterface;
import com.example.hostelmealmanagement.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        totalDepositAmount();
        totalExpenseAmount();
    }

    public void totalDepositAmount(){
        apiInterface.totalDepositAmount("Bearer "+token).enqueue(new Callback<TotalDepositAmountDataResponse>() {
            @Override
            public void onResponse(Call<TotalDepositAmountDataResponse> call, Response<TotalDepositAmountDataResponse> response) {
                if (response.code()==200){
                    totalDepositTextView.setText("$"+String.valueOf(response.body().getData()));
                }
                else if (response.code()==401){
                    Toast.makeText(HomeActivity.this, "Not Authorized to access this route", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(HomeActivity.this, "failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<TotalDepositAmountDataResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "failed", Toast.LENGTH_SHORT).show();

            }
        });

    }
    public  void totalExpenseAmount(){
        apiInterface.totalExpenseCost("Bearer "+token).enqueue(new Callback<TotalExpenseAmountDataResponse>() {
            @Override
            public void onResponse(Call<TotalExpenseAmountDataResponse> call, Response<TotalExpenseAmountDataResponse> response) {
               
            }

            @Override
            public void onFailure(Call<TotalExpenseAmountDataResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "failed", Toast.LENGTH_SHORT).show();

            }
        });
    }


}