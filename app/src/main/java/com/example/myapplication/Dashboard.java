package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

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

        btn_dietChart = (Button) findViewById(R.id.button2);

        btn_dietChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent usr_Lgn = new Intent(Dashboard.this, FoodDietChart.class);
                startActivity(usr_Lgn);
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
    }

}