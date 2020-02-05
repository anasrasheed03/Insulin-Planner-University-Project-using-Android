package com.example.myapplication.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.adapters.DisplayLogActivity;
import com.example.myapplication.R;

public class Dashboard extends AppCompatActivity {
    private Button btn_dietChart, btn_DctLgn, btn_UsrSgn, btn_DctSgn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        CardView user_Info = findViewById(R.id.usr_Info);
        CardView diet_Chart = findViewById(R.id.diet_Chart);
        CardView e_Book = findViewById(R.id.e_Book);
        CardView previous_Report = findViewById(R.id.prv_Rprt);
        CardView check_Cal = findViewById(R.id.chk_Cal);
//        CardView check_Cal = findViewById(R.id.chk_Cal);
        final String getUserName = getIntent().getStringExtra("userName");

        //Intent to Diet Chart
        diet_Chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent usr_Lgn = new Intent(Dashboard.this, FoodDietChart.class);
                startActivity(usr_Lgn);

            }
        });

        user_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent usr_Lgn = new Intent(Dashboard.this, Calculator.class);
                startActivity(usr_Lgn);
            }
        });
        //intent to Ebook
        e_Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ebokInt = new Intent(Dashboard.this, Ebook.class);
                startActivity(ebokInt);
            }
        });

        //intent to calories cal
        check_Cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calCalInt = new Intent(Dashboard.this, CalorieCal.class);
                calCalInt.putExtra("userName",getUserName);
                startActivity(calCalInt);
            }
        });

        // Intent to previous reports
        previous_Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prvRprtInt = new Intent(Dashboard.this, DisplayLogActivity.class);
                startActivity(prvRprtInt);
            }
        });

    }




}