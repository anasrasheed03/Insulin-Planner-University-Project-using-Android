package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class DocDashboard extends AppCompatActivity {
    private Button btn_dietChart, btn_DctLgn, btn_UsrSgn, btn_DctSgn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docdashboard);


        CardView user_Info = findViewById(R.id.usr_Info);
        CardView diet_Chart = findViewById(R.id.diet_Chart);
        CardView e_Book = findViewById(R.id.e_Book);
        CardView previous_Report = findViewById(R.id.prv_Rprt);


    }

}
