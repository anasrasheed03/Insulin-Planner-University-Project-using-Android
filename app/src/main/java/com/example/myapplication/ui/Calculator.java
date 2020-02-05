package com.example.myapplication.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class Calculator extends AppCompatActivity {
    private EditText bloodsugarEditText;
    private EditText carbRatioEditText;
    private EditText insulinSensitivityEditText;
    private EditText goalBGeditText;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String BG_TEXT = "BGtext";
    public static final String CARB_RATIO = "CarbRatio";
    public static final String INS_SENS = "InsSensitivity";
    public static final String GOAL_BG = "goalBG";

    private String bg_text;
    private String carb_ratio;
    private String ins_sens;
    private String goal_bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Button CalculateButton = findViewById(R.id.calculateButton);
        bloodsugarEditText = findViewById(R.id.bloodsugarEditText);
        carbRatioEditText = findViewById(R.id.carbRatioEditText);
        insulinSensitivityEditText = findViewById(R.id.sensitivityEditText);
        goalBGeditText = findViewById(R.id.goalBGeditText);

        CalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText carbEditText = findViewById(R.id.carbEditText);
                TextView insulinDoseTextView =findViewById(R.id.insulinDoseTextView);
                TextView correctionTextView = findViewById(R.id.correctionTextView);
                TextView carbTextView = findViewById(R.id.carbTextView);

                String temp_carb = carbEditText.getText().toString();
                String temp_BG = bloodsugarEditText.getText().toString();
                String temp_carbRatio = carbRatioEditText.getText().toString();
                String temp_insSens = insulinSensitivityEditText.getText().toString();
                String temp_goalBG = goalBGeditText.getText().toString();


                if (temp_carb.matches("") || temp_BG.matches("") || temp_carbRatio.matches("") || temp_insSens.matches("") || temp_goalBG.matches("")) {
                    Toast.makeText(Calculator.this,"Please fill in all the information before calculating", Toast.LENGTH_LONG).show();
                }
                else {

                    double carb = Double.parseDouble(temp_carb);
                    double bloodsugar = Double.parseDouble(temp_BG);
                    double carbRatio = Double.parseDouble(temp_carbRatio);
                    double insulinSensitivity = Double.parseDouble(temp_insSens);
                    double goalBG = Double.parseDouble(temp_goalBG);

                    //Calculating the dose

                    double kar = carb / carbRatio;
                    String carbDose = String.format("%.1f", kar);
                    double corrStart = bloodsugar - goalBG;
                    double corre = corrStart / insulinSensitivity;
                    String correction = String.format("%.1f", corre);
                    double ins = corre + kar;
                    final String insulinDose = String.format("%.1f", ins);
                    double vl = Double.parseDouble(insulinDose);

                    insulinDoseTextView.setText("The total dosage is " + insulinDose + " units");
                    correctionTextView.setText("The correction accounts for " + correction + " units");
                    carbTextView.setText("The carbs account for " + carbDose + " units");
                    if(vl > 3){
                        Toast.makeText(Calculator.this,"You may need to visit a doctor",Toast.LENGTH_SHORT).show();
                    }
                    saveData();
                }
            }
        });
        loadData();
        updateViews();
//        return view;

    }

    public void saveData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(BG_TEXT, goalBGeditText.getText().toString());
        editor.putString(CARB_RATIO, carbRatioEditText.getText().toString());
        editor.putString(INS_SENS, insulinSensitivityEditText.getText().toString());
        editor.putString(GOAL_BG, goalBGeditText.getText().toString());

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        bg_text = sharedPreferences.getString(BG_TEXT, "");
        carb_ratio = sharedPreferences.getString(CARB_RATIO, "");
        ins_sens = sharedPreferences.getString(INS_SENS, "");
        goal_bg = sharedPreferences.getString(GOAL_BG, "");
    }

    public void updateViews() {
        bloodsugarEditText.setText(bg_text);
        carbRatioEditText.setText(carb_ratio);
        insulinSensitivityEditText.setText(ins_sens);
        goalBGeditText.setText(goal_bg);
    }

}
