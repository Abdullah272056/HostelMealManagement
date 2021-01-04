package com.example.hostelmealmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelmealmanagement.expense.GetAllExpenseCustomAdapter;
import com.example.hostelmealmanagement.expense.GetAllExpenseData;
import com.example.hostelmealmanagement.expense.GetAllExpenseDataResponse;
import com.example.hostelmealmanagement.get_all_member.GetAllMemberDataResponse;
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
RecyclerView expenseRecyclerView;
String token;
List<GetAllExpenseData> getAllExpenseDataList;

GetAllExpenseCustomAdapter getAllExpenseCustomAdapter;

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
       // selectMarketerTextView=findViewById(R.id.selectMarketerTextViewId);
        expenseRecyclerView=findViewById(R.id.expenseRecyclerViewId);

        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpense();
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
                           if (response.body().getSuccess()==true){
                           getAllExpenseDataList=new ArrayList<>();
                           getAllExpenseDataList.addAll(response.body().getGetAllExpenseDataList());
                           if (getAllExpenseDataList.size ()>0){
                               getAllExpenseCustomAdapter=new GetAllExpenseCustomAdapter(
                                       ExpensesActivity.this,token,getAllExpenseDataList);
                               expenseRecyclerView.setLayoutManager(new LinearLayoutManager(ExpensesActivity.this));
                               expenseRecyclerView.setAdapter(getAllExpenseCustomAdapter);
                           }

                           Toast.makeText(ExpensesActivity.this, String.valueOf(getAllExpenseDataList.size())+"success", Toast.LENGTH_SHORT).show();
                           }
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

    private void addExpense(){

        AlertDialog.Builder builder     =new AlertDialog.Builder(ExpensesActivity.this);
        LayoutInflater layoutInflater   =LayoutInflater.from(ExpensesActivity.this);
        View view                       =layoutInflater.inflate(R.layout.add_expenses,null);
        builder.setView(view);
        final AlertDialog alertDialog   = builder.create();

        selectMarketerTextView=view.findViewById(R.id.selectMarketerTextViewId);
        selectMarketerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface.getAllMember("Bearer "+token).enqueue(new Callback<GetAllMemberDataResponse>() {
                    @Override
                    public void onResponse(Call<GetAllMemberDataResponse> call, Response<GetAllMemberDataResponse> response) {
                        if (response.code()==200){
                            Toast.makeText(ExpensesActivity.this, "sss", Toast.LENGTH_SHORT).show();
                        }else if (response.code()==401){
                            Toast.makeText(ExpensesActivity.this, "Not Authorized to access this route", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ExpensesActivity.this, "fff", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetAllMemberDataResponse> call, Throwable t) {
                        Toast.makeText(ExpensesActivity.this, "fff", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        alertDialog.show();
    }


}