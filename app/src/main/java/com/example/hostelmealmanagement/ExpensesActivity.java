package com.example.hostelmealmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelmealmanagement.expense.GetAllExpenseCustomAdapter;
import com.example.hostelmealmanagement.expense.GetAllExpenseData;
import com.example.hostelmealmanagement.expense.GetAllExpenseDataResponse;
import com.example.hostelmealmanagement.expense.create_expense.CreateExpenseGateDataResponse;
import com.example.hostelmealmanagement.expense.create_expense.CreateExpenseSetData;
import com.example.hostelmealmanagement.get_all_member.GetAllMemberData;
import com.example.hostelmealmanagement.get_all_member.GetAllMemberDataResponse;
import com.example.hostelmealmanagement.get_all_member.MemberListCustomAdapter;
import com.example.hostelmealmanagement.retrofit.ApiInterface;
import com.example.hostelmealmanagement.retrofit.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpensesActivity extends AppCompatActivity implements MemberListCustomAdapter.OnContactClickListener{
FloatingActionButton addExpenseButton;

// 1s alert
TextView selectMarketerTextView;
Button saveExpenseButton;
EditText expenseNameEditText,typeEditText,costEditText;
ProgressBar createExpenseProgressBar;
String marketerId=null;

RecyclerView expenseRecyclerView;
RecyclerView memberRecyclerView;
ProgressBar memberProgressBar;
ProgressBar expenseProgressBar;
String token;
List<GetAllExpenseData> getAllExpenseDataList;
List<GetAllMemberData> getAllMemberDataList;



GetAllExpenseCustomAdapter getAllExpenseCustomAdapter;
MemberListCustomAdapter memberListCustomAdapter;

ApiInterface apiInterface;
AlertDialog alertDialog1;

MemberListCustomAdapter.OnContactClickListener onContactClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        onContactClickListener=this;
        //receive token
        token=getIntent().getStringExtra("token");
        //initialize apiInterface
        apiInterface = RetrofitClient.getRetrofit("http://hostel-meal-calc.herokuapp.com/").create(ApiInterface.class);

        addExpenseButton=findViewById(R.id.addExpenseButtonId);
       // selectMarketerTextView=findViewById(R.id.selectMarketerTextViewId);
        expenseRecyclerView=findViewById(R.id.expenseRecyclerViewId);
        expenseProgressBar=findViewById(R.id.expenseProgressBarId);

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
                       }
                       else if (response.code()==404){
                           Toast.makeText(ExpensesActivity.this, "No expenses added so far", Toast.LENGTH_SHORT).show();
                       }
                       else {
                           Toast.makeText(ExpensesActivity.this, "error", Toast.LENGTH_SHORT).show();
                       }
                      }catch (Exception e){

                      }
                       expenseProgressBar.setVisibility(View.INVISIBLE);
                   }

                   @Override
                   public void onFailure(Call<GetAllExpenseDataResponse> call, Throwable t) {
                       Toast.makeText(ExpensesActivity.this, "failed", Toast.LENGTH_SHORT).show();
                       expenseProgressBar.setVisibility(View.INVISIBLE);
                   }
               });

        }

        private void addExpense(){

        AlertDialog.Builder builder     =new AlertDialog.Builder(ExpensesActivity.this);
        LayoutInflater layoutInflater   =LayoutInflater.from(ExpensesActivity.this);
        View view                       =layoutInflater.inflate(R.layout.add_expenses,null);
        builder.setView(view);
        final AlertDialog alertDialog   = builder.create();

            saveExpenseButton           =view.findViewById(R.id.saveExpenseButtonId);
            selectMarketerTextView      =view.findViewById(R.id.selectMarketerTextViewId);
            expenseNameEditText         =view.findViewById(R.id.expenseNameEditTextId);
            typeEditText                =view.findViewById(R.id.typeEditTextId);
            costEditText                =view.findViewById(R.id.costEditTextId);
            createExpenseProgressBar    =view.findViewById(R.id.createExpenseProgressBarId);

        selectMarketerTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder     =new AlertDialog.Builder(ExpensesActivity.this);
                LayoutInflater layoutInflater   =LayoutInflater.from(ExpensesActivity.this);
                final View view                 =layoutInflater.inflate(R.layout.member_recycler_view,null);
                builder.setView(view);
                alertDialog1    = builder.create();


                memberRecyclerView  =view.findViewById(R.id.memberRecyclerViewId);
                memberProgressBar   =view.findViewById(R.id.memberProgressBarId);

                //Toast.makeText(ExpensesActivity.this, getAllMemberDataList.size()+"sss", Toast.LENGTH_SHORT).show();
                alertDialog1.show();

                apiInterface.getAllMember("Bearer "+token).enqueue(new Callback<GetAllMemberDataResponse>() {
                    @Override
                    public void onResponse(Call<GetAllMemberDataResponse> call, Response<GetAllMemberDataResponse> response) {
                        if (response.code()==200){
                            if (response.body().getSuccess()==true){
                                getAllMemberDataList=new ArrayList<>();
                                getAllMemberDataList.addAll(response.body().getGetAllMemberDataList());
                                if (getAllMemberDataList.size()>0){

                                    memberListCustomAdapter=new MemberListCustomAdapter(
                                            ExpensesActivity.this,token,getAllMemberDataList,onContactClickListener);

                                    memberRecyclerView.setLayoutManager(new LinearLayoutManager(ExpensesActivity.this));
                                    memberRecyclerView.setAdapter(memberListCustomAdapter);
                                }
                            }
                        }else if (response.code()==401){
                            Toast.makeText(ExpensesActivity.this, "Not Authorized to access this route", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ExpensesActivity.this, "fff", Toast.LENGTH_SHORT).show();
                        }
                        memberProgressBar.setVisibility(View.INVISIBLE);
                    }
                    @Override
                    public void onFailure(Call<GetAllMemberDataResponse> call, Throwable t) {
                        Toast.makeText(ExpensesActivity.this, "fff", Toast.LENGTH_SHORT).show();
                        memberProgressBar.setVisibility(View.INVISIBLE);

                    }
                });
            }
        });

            saveExpenseButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    String expenseName  =expenseNameEditText.getText().toString();
                    String expenseType  =typeEditText.getText().toString();
                    String expenseCost  =costEditText.getText().toString();
                    String marketerName =selectMarketerTextView.getText().toString();

                    if (TextUtils.isEmpty(expenseName)){
                        expenseNameEditText.setError("Enter expense name");
                        expenseNameEditText.requestFocus();
                        return;
                    }
                    if (expenseName.length()<4){
                        expenseNameEditText.setError("should be at least 4 character");
                        expenseNameEditText.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(expenseType)){
                        typeEditText.setError("Enter expense type");
                        typeEditText.requestFocus();
                        return;
                    }
                    if (expenseType.length()<4){
                        typeEditText.setError("should be at least 4 character");
                        typeEditText.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(expenseCost)){
                        costEditText.setError("Enter expense cost");
                        costEditText.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(marketerName)){
                        selectMarketerTextView.setError("please select marketer");
                        selectMarketerTextView.requestFocus();
                        return;
                    }
                    if (marketerId.length()>0 && marketerId!=null){
                        createExpenseProgressBar.setVisibility(View.VISIBLE);
                        CreateExpenseSetData createExpenseSetData= new CreateExpenseSetData(marketerId,Integer.parseInt(expenseCost),expenseType,expenseName);
                        apiInterface.createExpense("Bearer "+token,createExpenseSetData).enqueue(new Callback<CreateExpenseGateDataResponse>() {
                            @Override
                            public void onResponse(Call<CreateExpenseGateDataResponse> call, Response<CreateExpenseGateDataResponse> response) {
                                if (response.code()==200){
                                    Toast.makeText(ExpensesActivity.this, "create success", Toast.LENGTH_SHORT).show();
                                    alertDialog.dismiss();
                                    getAllExpense();
                                }
                                else if(response.code()==400){
                                    Toast.makeText(ExpensesActivity.this, "User not valid", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(ExpensesActivity.this, "failed !try again", Toast.LENGTH_SHORT).show();
                                }
                                createExpenseProgressBar.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onFailure(Call<CreateExpenseGateDataResponse> call, Throwable t) {
                                Toast.makeText(ExpensesActivity.this, "failed !try again", Toast.LENGTH_SHORT).show();
                                createExpenseProgressBar.setVisibility(View.INVISIBLE);
                            }
                        });
                    }else {
                        Toast.makeText(ExpensesActivity.this, "Marketer Id not found", Toast.LENGTH_SHORT).show();
                    }








                }
            });



        alertDialog.show();
    }


        @Override
        public void onContactClick(int position){
            alertDialog1.dismiss();
            Toast.makeText(this, "ssssdd", Toast.LENGTH_SHORT).show();
            marketerId=String.valueOf(getAllMemberDataList.get(position).getId());
            selectMarketerTextView.setText(String.valueOf(getAllMemberDataList.get(position).getName()));
            selectMarketerTextView.setError(null);
        }
}