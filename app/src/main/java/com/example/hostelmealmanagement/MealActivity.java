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

public class MealActivity extends AppCompatActivity implements MemberListCustomAdapter.OnContactClickListener  {
    FloatingActionButton addMealFloatingButton;
    String token;
    ApiInterface apiInterface;

    // alertBox
    EditText mealNumberEditText;
    TextView selectBorderTextView;
    Button saveMealButton;
    AlertDialog alertDialog1;
    RecyclerView memberRecyclerView;
    ProgressBar memberProgressBar;
    List<GetAllMemberData> getAllMemberDataList;
    MemberListCustomAdapter memberListCustomAdapter;
    MemberListCustomAdapter.OnContactClickListener onContactClickListener;
    String borderId=null;
    ProgressBar addDepositProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        onContactClickListener=this;
        //receive user token
        token= getIntent().getStringExtra("token");
        //initialize apiInterface
        apiInterface = RetrofitClient.getRetrofit("http://hostel-meal-calc.herokuapp.com/").create(ApiInterface.class);

        addMealFloatingButton=findViewById(R.id.addMealFloatingButtonId);
        addMealFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMeal();
            }
        });
    }


    public void addMeal(){
        AlertDialog.Builder builder     =new AlertDialog.Builder(MealActivity.this);
        LayoutInflater layoutInflater   =LayoutInflater.from(MealActivity.this);
        View view                       =layoutInflater.inflate(R.layout.add_meal,null);
        builder.setView(view);
        final AlertDialog alertDialog   = builder.create();

        mealNumberEditText=view.findViewById(R.id.mealNumberEditTextId);
        selectBorderTextView=view.findViewById(R.id.selectBorderTextViewId);
        saveMealButton=view.findViewById(R.id.saveMealButtonId);

        selectBorderTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder     =new AlertDialog.Builder(MealActivity.this);
                LayoutInflater layoutInflater   =LayoutInflater.from(MealActivity.this);
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
                                            MealActivity.this,token,getAllMemberDataList,onContactClickListener);

                                    memberRecyclerView.setLayoutManager(new LinearLayoutManager(MealActivity.this));
                                    memberRecyclerView.setAdapter(memberListCustomAdapter);
                                }
                            }
                        }else if (response.code()==401){
                            Toast.makeText(MealActivity.this, "Not Authorized to access this route", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MealActivity.this, "fff", Toast.LENGTH_SHORT).show();
                        }
                        memberProgressBar.setVisibility(View.INVISIBLE);
                    }
                    @Override
                    public void onFailure(Call<GetAllMemberDataResponse> call, Throwable t) {
                        Toast.makeText(MealActivity.this, "fff", Toast.LENGTH_SHORT).show();
                        memberProgressBar.setVisibility(View.INVISIBLE);

                    }
                });

            }
        });

        saveMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mealNumber=mealNumberEditText.getText().toString();
                String selectBorder=selectBorderTextView.getText().toString();
                if (TextUtils.isEmpty(mealNumber)){
                    mealNumberEditText.setError("Enter meal number");
                    mealNumberEditText.requestFocus();
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
        alertDialog1.dismiss();
        borderId=String.valueOf(getAllMemberDataList.get(position).getId());
        selectBorderTextView.setText(String.valueOf(getAllMemberDataList.get(position).getName()));
        selectBorderTextView.setError(null);

    }
}