package com.example.myapplication.model;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.db.CalorieSummary;
import com.example.myapplication.adapters.DisplayLogActivity;
import com.example.myapplication.db.LogEntry;
import com.example.myapplication.db.LogManager;
import com.example.myapplication.R;
import com.example.myapplication.db.SQLiteHandler;
import com.example.myapplication.ui.AddNewEntry;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DataClass extends AppCompatActivity {

    TextView result1;
    boolean doubleBackToExitPressedOnce = false;
    private Context context;
    private List<LogEntry> logEntries;
    private DisplayLogActivity disp;
    private CalorieSummary calSum;
    private boolean entry1;
    private double ototal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_class);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataClass.this, AddNewEntry.class);
                startActivity(intent);
            }
        });

        retrieveCalorieLog();
        displayCalorieSummary();
        displaycal();


        retrieveCalorieLog();
        displayCalorieSummary();
        displaycal();



        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
        Users user = db.getUser();

        //Bundle a = getIntent().getExtras();



        String namevr = user.getName();
        String resulttext = user.getResult();

        result1 = (TextView) findViewById(R.id.resulttext);


        getSupportActionBar().setTitle("Result");

        result1.setText(resulttext);


        db.close();
    }

    private void displaycal() {
        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
        int flag = 0;
        Users user = db.getUser();
        float str1 = calSum.getTotalCalories();
        float str = calSum.getTotalcalories1();



        double total = (double) str;
        double ntotal = (double) str1;

        double result;
        double tcal = user.getCalories();
        double daily = user.getDaily();
        double dispCal = tcal;

        String date1 = user.getDates();



        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = calendar.getTime();
        String date2 = dateFormat.format(today);



        //     daily = daily - add1;
        //     db.update_daily(user.getCalories(), user.getName());


        if (user.getTempo() == ntotal && (date2.equals(user.getDates()))) {
            flag = 1;
        } else if (user.getTempo() == ntotal && (!date2.equals(user.getDates()))){
            flag = 2;
        } else if (user.getTempo() != ntotal && (date2.equals(user.getDates()))) {
            flag = 3;
        } else if (user.getTempo() != ntotal && (!date2.equals(user.getDates()))){
            flag = 4;
        }
        switch (flag) {
            case 1:

                dispCal = user.getDaily();
                break;
            case 2:

                dispCal = user.getCalories();
                //result = user.getCalories();


                break;
            case 3:

                dispCal = user.getDaily() - total;


                break;
            case 4:

                dispCal = user.getCalories() - total;

                break;
        }
        db.update_tempo(ntotal, user.getName());
        db.update_daily(dispCal, user.getName());
        db.update_Users(date2, user.getName());


        daily = user.getDaily();
        TextView textView = (TextView) findViewById(R.id.delete);
        if (daily < 0) {
            textView.setText( String.format( "%.1f",  Math.abs(daily)) + "\n" + "calories over");
        } else textView.setText( String.format( "%.1f",  daily ) + "\n" + "calories left");


        db.close();
    }



    private void retrieveCalorieLog() {
        LogManager.loadCalorieLog(this);
    }

    private void displayCalorieSummary() {
        calSum = new CalorieSummary(LogManager.getLogEntries());
        calSum.display(this);
    }


    protected void onResume() {
        super.onResume();

        displayCalorieSummary();
        displaycal();
        displaycal();
    }

    public void reset(View view) {

        Context context = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to reset your daily calorie?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialog.dismiss();
                SQLiteHandler db = new SQLiteHandler(getApplicationContext());
                Users user = db.getUser();
                user.getCalories();
                db.update_daily(user.getCalories(), user.getName());
                displaycal();
                db.close();   // stop chronometer here

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

}

//    }
//}
