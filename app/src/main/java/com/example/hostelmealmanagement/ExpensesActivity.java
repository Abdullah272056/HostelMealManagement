package com.example.hostelmealmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelmealmanagement.expense.GetAllExpenseData;
import com.example.hostelmealmanagement.expense.GetAllExpenseDataResponse;
import com.example.hostelmealmanagement.retrofit.ApiInterface;
import com.example.hostelmealmanagement.retrofit.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpensesActivity extends AppCompatActivity {
FloatingActionButton addExpenseButton;
TextView selectMarketerTextView;
String token;
List<GetAllExpenseData> getAllExpenseDataList;

Spinner spinner;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        //receive token
        token=getIntent().getStringExtra("token");
        //initialize apiInterface
        apiInterface = RetrofitClient.getRetrofit("http://hostel-meal-calc.herokuapp.com/").create(ApiInterface.class);

        addExpenseButton=findViewById(R.id.addExpenseButtonId);
        selectMarketerTextView=findViewById(R.id.selectMarketerTextViewId);

        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCustomerInformation();
            }
        });


        getAllExpense();

    }
        public  void getAllExpense(){
               apiInterface.getAllExpense("Bearer "+token).enqueue(new Callback<GetAllExpenseDataResponse>() {
                   @Override
                   public void onResponse(Call<GetAllExpenseDataResponse> call, Response<GetAllExpenseDataResponse> response) {
                      try {
                       if (response.code()==200){
                           getAllExpenseDataList=new ArrayList<>();
                           getAllExpenseDataList.addAll(response.body().getGetAllExpenseDataList());
                           Toast.makeText(ExpensesActivity.this, String.valueOf(getAllExpenseDataList.size())+"success", Toast.LENGTH_SHORT).show();
                       }
                       else if (response.code()==401){
                           Toast.makeText(ExpensesActivity.this, "Not Authorized to access this route", Toast.LENGTH_SHORT).show();

                       }else {
                           Toast.makeText(ExpensesActivity.this, "error", Toast.LENGTH_SHORT).show();
                       }
                      }catch (Exception e){

                      }
                   }

                   @Override
                   public void onFailure(Call<GetAllExpenseDataResponse> call, Throwable t) {
                       Toast.makeText(ExpensesActivity.this, "failed", Toast.LENGTH_SHORT).show();
                   }
               });

        }

    private void addCustomerInformation(){

        AlertDialog.Builder builder     =new AlertDialog.Builder(ExpensesActivity.this);
        LayoutInflater layoutInflater   =LayoutInflater.from(ExpensesActivity.this);
        View view                       =layoutInflater.inflate(R.layout.add_expenses,null);
        builder.setView(view);
        final AlertDialog alertDialog   = builder.create();

//        spinner=view.findViewById(R.id.select);
//
//        //Creating the ArrayAdapter instance having the country list
//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,contacts);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Setting the ArrayAdapter data on the Spinner
//        spinner.setAdapter(aa);

        alertDialog.show();
    }
}