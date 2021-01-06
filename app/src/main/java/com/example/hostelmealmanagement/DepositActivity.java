package com.example.hostelmealmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class DepositActivity extends AppCompatActivity implements MemberListCustomAdapter.OnContactClickListener {
FloatingActionButton addDepositFloatingButton;
ApiInterface apiInterface;
String token;

// alert box
    EditText depositAmountEditText;
    TextView selectBorderTextView;
    Button saveDepositButton;
    AlertDialog alertDialog1;
    RecyclerView memberRecyclerView;
    ProgressBar memberProgressBar;
    List<GetAllMemberData> getAllMemberDataList;
    MemberListCustomAdapter memberListCustomAdapter;
    MemberListCustomAdapter.OnContactClickListener onContactClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        onContactClickListener=this;
        //receive user token
        token= getIntent().getStringExtra("token");
        //initialize apiInterface
        apiInterface = RetrofitClient.getRetrofit("http://hostel-meal-calc.herokuapp.com/").create(ApiInterface.class);

        //view finding
        addDepositFloatingButton=findViewById(R.id.addDepositFloatingButtonId);
        addDepositFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDeposit();
            }
        });
    }

    public  void  addDeposit(){
        AlertDialog.Builder builder     =new AlertDialog.Builder(DepositActivity.this);
        LayoutInflater layoutInflater   =LayoutInflater.from(DepositActivity.this);
        View view                       =layoutInflater.inflate(R.layout.add_deposit,null);
        builder.setView(view);
        final AlertDialog alertDialog   = builder.create();

        depositAmountEditText=view.findViewById(R.id.depositAmountEditTextId);
        selectBorderTextView=view.findViewById(R.id.selectBorderTextViewId);
        saveDepositButton=view.findViewById(R.id.saveDepositButtonId);

        selectBorderTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder     =new AlertDialog.Builder(DepositActivity.this);
                LayoutInflater layoutInflater   =LayoutInflater.from(DepositActivity.this);
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
                                            DepositActivity.this,token,getAllMemberDataList,onContactClickListener);

                                    memberRecyclerView.setLayoutManager(new LinearLayoutManager(DepositActivity.this));
                                    memberRecyclerView.setAdapter(memberListCustomAdapter);
                                }
                            }
                        }else if (response.code()==401){
                            Toast.makeText(DepositActivity.this, "Not Authorized to access this route", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(DepositActivity.this, "fff", Toast.LENGTH_SHORT).show();
                        }
                        memberProgressBar.setVisibility(View.INVISIBLE);
                    }
                    @Override
                    public void onFailure(Call<GetAllMemberDataResponse> call, Throwable t) {
                        Toast.makeText(DepositActivity.this, "fff", Toast.LENGTH_SHORT).show();
                        memberProgressBar.setVisibility(View.INVISIBLE);

                    }
                });
            }
        });



        saveDepositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String depositAmount=depositAmountEditText.getText().toString();
                String selectBorder=selectBorderTextView.getText().toString();
                if (TextUtils.isEmpty(depositAmount)){
                    depositAmountEditText.setError("Enter deposit amount");
                    depositAmountEditText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(selectBorder)){
                    selectBorderTextView.setError("please select member");
                    selectBorderTextView.requestFocus();
                    return;
                }
            }
        });


        alertDialog.show();


    }

    @Override
    public void onContactClick(int position) {

    }
}