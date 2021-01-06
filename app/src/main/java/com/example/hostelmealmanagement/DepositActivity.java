package com.example.hostelmealmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hostelmealmanagement.retrofit.ApiInterface;
import com.example.hostelmealmanagement.retrofit.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DepositActivity extends AppCompatActivity {
FloatingActionButton addDepositFloatingButton;
ApiInterface apiInterface;
String token;

// alert box
    EditText depositAmountEditText;
    TextView selectBorderTextView;
    Button saveDepositButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
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
}