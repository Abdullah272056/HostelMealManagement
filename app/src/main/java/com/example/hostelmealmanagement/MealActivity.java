package com.example.hostelmealmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MealActivity extends AppCompatActivity {
    FloatingActionButton addMealFloatingButton;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        //receive user token
        token= getIntent().getStringExtra("token");

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





        alertDialog.show();
    }
}