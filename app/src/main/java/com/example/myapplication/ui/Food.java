package com.example.myapplication.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.myapplication.R;
//import com.example.myapplication.SessionManager;
import com.example.myapplication.db.SessionManager;

import java.util.HashMap;

public class Food extends AppCompatActivity {
    private String foodName;
    private String foodQuantity;
    private String foodCalorie;


    public SessionManager sessionManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_diet_chart);
        //getting the recyclerview from xml
    }
    public Food(String foodName, String foodQuantity, String foodCalorie) {
        this.foodName = foodName;
        this.foodQuantity = foodQuantity;
        this.foodCalorie = foodCalorie;
    }

    public String getfood_name() {
        return foodName;
    }


    public String getFoodQuantity() {
        return foodQuantity;
    }

    public String getFoodCalorie() {
        return foodCalorie;
    }

}

