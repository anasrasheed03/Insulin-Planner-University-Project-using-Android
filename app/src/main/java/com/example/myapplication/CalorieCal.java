package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalorieCal extends AppCompatActivity {

    EditText weight, height, age;
    double bmi;
    double bmr;
    RadioButton radioGender;
    RadioButton stars;
    int selectedId;
    int radioId;
    private String resulttext;
    private TextView txtresult, name;
    Spinner spgoals;
    String sgoals;

    String goals[] = { "Maintain Weight", "Gain 0.5kg per week", "Gain 1kg per week", "Lose 0.5kg per week",
            "Lose 1kg per week" };
    ArrayAdapter<String> adaptergoals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_cal);
        spgoals = (Spinner) findViewById(R.id.spinner);



        CustomFonts.setDefaultFont(this, "DEFAULT", "fonts/Gotham.ttf");
        CustomFonts.setDefaultFont(this, "MONOSPACE", "fonts/Gotham.ttf");
        CustomFonts.setDefaultFont(this, "SERIF", "fonts/Gotham.ttf");
        CustomFonts.setDefaultFont(this, "SANS_SERIF", "fonts/Gotham.ttf");
        name = (TextView) findViewById(R.id.namevar);
        String getUserName = getIntent().getStringExtra("userName");
//        Log.d("User Name", getUserName);
        name.setText(getUserName);
        adaptergoals = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, goals);


        adaptergoals.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spgoals.setAdapter(adaptergoals);

        Button one= (Button) findViewById(R.id.calc);
        one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                weight = (EditText) findViewById(R.id.weight);
                height = (EditText) findViewById(R.id.height);
                age = (EditText) findViewById(R.id.age);

                String ass;

                int Gender, starss;

                RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
                RadioButton male = (RadioButton) findViewById(R.id.Male);
                RadioButton female = (RadioButton) findViewById(R.id.Female);

                selectedId = rg.getCheckedRadioButtonId();
                radioGender = (RadioButton) findViewById(selectedId);




                if (male.isChecked()) {
                    Gender = 1;
                } else {
                    Gender = 2;
                }

                if (TextUtils.isEmpty(name.getText().toString())) {
                    name.setError("Please enter your name");
                    Toast.makeText(CalorieCal.this, "Please enter your name",
                            Toast.LENGTH_LONG).show();
                    return;
                }


                if (TextUtils.isEmpty(weight.getText().toString())) {
                    weight.setError("Please enter your weight");
                    return;
                }


                if (TextUtils.isEmpty(height.getText().toString())) {
                    height.setError("Please enter your height");
                    return;
                }

                if (TextUtils.isEmpty(age.getText().toString())) {
                    age.setError("Please enter your age");
                    return;
                }







                Intent i = new Intent(CalorieCal.this, DataClass.class);

                String namevr = name.getText().toString();
                double weightvr = Double.parseDouble(weight.getText().toString());
                double heightvr = Double.parseDouble(height.getText().toString());
                int agevr = Integer.parseInt(age.getText().toString());


                //a.putString("name", String.valueOf(namevr));


                if ((weightvr <= 30) || (weightvr >= 130)) {
                    Toast.makeText(CalorieCal.this, "Please enter a valid weight",
                            Toast.LENGTH_SHORT).show();
                    return;

                } else if ((heightvr <= 120) || (heightvr >= 205)) {
                    Toast.makeText(CalorieCal.this, "Please enter a valid height",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else if ((agevr <= 0) || (agevr >= 100)) {
                    Toast.makeText(CalorieCal.this, "Please enter a valid age",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    if (selectedId == R.id.Male) {
                        bmr = (((weightvr * 10) + (heightvr * 6.25)) - (5 * agevr)) + 5;

                    } else {
                        bmr = (((weightvr * 10) + (heightvr * 6.25)) - (5 * agevr)) - 161;
                    }

                    RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioGroup1);

                    radioId = rg1.getCheckedRadioButtonId();
                    stars = (RadioButton) findViewById(radioId);
                    RadioButton r1 = (RadioButton) findViewById(R.id.r1);
                    RadioButton r2 = (RadioButton) findViewById(R.id.r2);
                    RadioButton r3 = (RadioButton) findViewById(R.id.r3);
                    RadioButton r4 = (RadioButton) findViewById(R.id.r4);
                    RadioButton r5 = (RadioButton) findViewById(R.id.r5);

                    if (r1.isChecked()) {
                        starss = 1;
                    } else if (r2.isChecked()) {
                        starss = 2;

                    } else if (r3.isChecked()) {
                        starss = 3;
                    } else if (r4.isChecked()) {
                        starss = 4;
                    } else {
                        starss = 5;
                    }


                    if (radioId == R.id.r1) {
                        bmr = bmr * 1.2;
                    } else if (radioId == R.id.r2) {
                        bmr = bmr * 1.375;
                    } else if (radioId == R.id.r3) {
                        bmr = bmr * 1.55;
                    } else if (radioId == R.id.r4) {
                        bmr = bmr * 1.725;
                    } else {
                        bmr = bmr * 1.9;
                    }


                    bmi = (float) (weightvr / ((heightvr / 100) * (heightvr / 100)));

                    if (bmi < 16) {
                        ass = "Severely underweight";
                    } else if (bmi < 18.5) {

                        ass = "Underweight";
                    } else if (bmi < 25) {

                        ass = "Normal";
                    } else if (bmi < 30) {

                        ass = "Overweight";
                    } else {
                        ass = "Obese";
                    }
                    sgoals= spgoals.getSelectedItem().toString();

                    if (sgoals == "Maintain Weight"){
                        bmr = bmr;
                    } else if (sgoals == "Gain 0.5kg per week") {
                        bmr = bmr + 500;
                    } else if (sgoals == "Gain 1kg per week") {
                        bmr = bmr + 1000;
                    } else if (sgoals == "Lose 0.5kg per week") {
                        bmr = bmr - 500;
                    } else if (sgoals == "Lose 1kg per week") {
                        bmr = bmr - 1000;
                    }

                    txtresult = (TextView) findViewById(R.id.result1);
                    resulttext = "Your BMI is: " + String.format("%.2f", bmi) + "\nYou are " + ass;
                    double daily = bmr;
                    String results = resulttext;
                    double calories = bmr;
                    double tempo = 0.0;
                    Calendar calendar = Calendar.getInstance();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


                    Date today = calendar.getTime();

                    String dates = dateFormat.format(today);


                    SQLiteHandler db = new SQLiteHandler(getApplicationContext());
                    db.addUser(namevr, Gender, agevr, weightvr, heightvr, starss, results, calories, dates, daily, tempo);
//                   if (db.isUserEmpty()) {
//                         db.addUser(namevr, Gender, agevr, weightvr, heightvr, starss, results, calories, dates, daily, tempo);
//                        db.updatetable(namevr, Gender, agevr, weightvr, heightvr, starss, results, calories, dates, daily);
//                    }
//                    else {
//                        db.updatetable(namevr, Gender, agevr, weightvr, heightvr, starss, results, calories, dates, daily);
//                   }
                    db.close();
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}

//    }
//}
