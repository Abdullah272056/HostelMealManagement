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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hostelmealmanagement.expense.GetAllExpenseCustomAdapter;
import com.example.hostelmealmanagement.expense.GetAllExpenseData;
import com.example.hostelmealmanagement.expense.GetAllExpenseDataResponse;
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
TextView selectMarketerTextView;
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

                       }else {
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

        selectMarketerTextView=view.findViewById(R.id.selectMarketerTextViewId);
        selectMarketerTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder     =new AlertDialog.Builder(ExpensesActivity.this);
                LayoutInflater layoutInflater   =LayoutInflater.from(ExpensesActivity.this);
                final View view                       =layoutInflater.inflate(R.layout.member_recycler_view,null);
                builder.setView(view);
               alertDialog1   = builder.create();
                memberRecyclerView=view.findViewById(R.id.memberRecyclerViewId);
                memberProgressBar=view.findViewById(R.id.memberProgressBarId);


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

        alertDialog.show();
    }


        @Override
        public void onContactClick(int position){
            alertDialog1.dismiss();
            Toast.makeText(this, "ssssdd", Toast.LENGTH_SHORT).show();
            selectMarketerTextView.setText(String.valueOf(getAllMemberDataList.get(position).getName()));

        }
}