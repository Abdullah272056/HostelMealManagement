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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ExpensesActivity extends AppCompatActivity {
FloatingActionButton addExpenseButton;
TextView selectMarketerTextView;
Spinner spinner;
    String contacts[]={"Ajay","Sachin","Sumit","Tarun","Yogesh"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        addExpenseButton=findViewById(R.id.addExpenseButtonId);
        selectMarketerTextView=findViewById(R.id.selectMarketerTextViewId);

        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCustomerInformation();
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