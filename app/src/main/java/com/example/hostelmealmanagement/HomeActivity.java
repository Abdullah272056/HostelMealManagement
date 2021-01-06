package com.example.hostelmealmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelmealmanagement.meal.MealRateDataResponse;
import com.example.hostelmealmanagement.retrofit.ApiInterface;
import com.example.hostelmealmanagement.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    TextView totalDepositTextView,totalExpenseTextView,totalMealTextView,mealRateTextView;
    String token;

    ApiInterface apiInterface;

    Button expenseButton,calculationButton,depositButton,mealButton;
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
        expenseButton=findViewById(R.id.expenseButtonId);
        calculationButton=findViewById(R.id.calculationButtonId);
        depositButton=findViewById(R.id.depositButtonId);
        mealButton=findViewById(R.id.mealButtonId);
        // call method
        totalDepositAmount();
        totalExpenseAmount();
        totalMealCount();
        mealRate();

        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this,ExpensesActivity.class);
                intent.putExtra("token",token);
                startActivity(intent);
            }
        });
        calculationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this,CalculationActivity.class);
                intent.putExtra("token",token);
                startActivity(intent);
            }
        });
        depositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this,DepositActivity.class);
                intent.putExtra("token",token);
                startActivity(intent);
            }
        });
        mealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this,MealActivity.class);
                intent.putExtra("token",token);
                startActivity(intent);
            }
        });
    }

    public void totalDepositAmount(){
        apiInterface.totalDepositAmount("Bearer "+token).enqueue(new Callback<HomePageDataResponse>() {
            @Override
            public void onResponse(Call<HomePageDataResponse> call, Response<HomePageDataResponse> response) {
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
            public void onFailure(Call<HomePageDataResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "failed", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public  void totalExpenseAmount(){
        apiInterface.totalExpenseCost("Bearer "+token).enqueue(new Callback<HomePageDataResponse>() {
            @Override
            public void onResponse(Call<HomePageDataResponse> call, Response<HomePageDataResponse> response) {
                if (response.code()==200){
                    totalExpenseTextView.setText("$"+String.valueOf(response.body().getData()));
                }
                else if (response.code()==401){
                    Toast.makeText(HomeActivity.this, "Not Authorized to access this route", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(HomeActivity.this, "failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<HomePageDataResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public  void totalMealCount(){
        apiInterface.totalMealCount("Bearer "+token).enqueue(new Callback<HomePageDataResponse>() {
            @Override
            public void onResponse(Call<HomePageDataResponse> call, Response<HomePageDataResponse> response) {
                if (response.code()==200){
                    totalMealTextView.setText(String.valueOf(response.body().getData()));
                }
                else if (response.code()==401){
                    Toast.makeText(HomeActivity.this, "Not Authorized to access this route", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(HomeActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HomePageDataResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public  void mealRate(){
        apiInterface.mealRate("Bearer "+token).enqueue(new Callback<MealRateDataResponse>() {
            @Override
            public void onResponse(Call<MealRateDataResponse> call, Response<MealRateDataResponse> response) {
                if (response.code()==200){
                    mealRateTextView.setText("$"+String.valueOf(response.body().getData()));
                }
                else if (response.code()==401){
                    Toast.makeText(HomeActivity.this, "Not Authorized to access this route", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(HomeActivity.this, "failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<MealRateDataResponse> call, Throwable t) {
                Log.e("sasa",t.getMessage().toString());
                Toast.makeText(HomeActivity.this, "failed x", Toast.LENGTH_SHORT).show();

            }
        });
    }


}