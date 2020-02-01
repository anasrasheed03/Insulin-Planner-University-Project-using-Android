package com.example.myapplication;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

//import com.example.waseel.pharmadoc.R;
import com.android.volley.toolbox.Volley;
//import com.example.waseel.app.AppConfig;
//import com.example.waseel.app.AppController;
//import com.example.waseel.helper.SQLiteHandler;
//import com.example.waseel.helper.SessionManager;

public class UserSignUp extends AppCompatActivity {

    private EditText inputfirstname;
    private EditText inputlastname;
    private EditText inputbloodgroup;
    private EditText inputphonenumber;
    private EditText inputemail;
    private EditText inputpassword;
    private EditText inputdob;
    private Button btn_Register,btnLinkToLogin;
    private ProgressBar loading;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        inputfirstname = findViewById(R.id.f_name);
        inputlastname = findViewById(R.id.l_name);
        inputbloodgroup = findViewById(R.id.bloog_Grp);
        inputphonenumber = findViewById(R.id.phone);
        inputemail = findViewById(R.id.email);
        inputpassword = findViewById(R.id.password);
        loading = findViewById(R.id.loading);
        btn_Register = findViewById(R.id.btn_register);
        inputdob = findViewById(R.id.dob);
//        btnLinkToLogin = findViewById(R.id.btn_usrLogin);

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

        inputdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(UserSignUp.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                inputdob.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


//        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(),
//                        UserLogIn.class);
//                startActivity(i);
//                finish();
//            }
//        });
    }
    private void Register(){
        loading.setVisibility(View.VISIBLE);
        btn_Register.setVisibility(View.GONE);



        final String firstName = this.inputfirstname.getText().toString().trim();
        final String lastName = this.inputlastname.getText().toString().trim();
        final String email = this.inputemail.getText().toString().trim();
        final String password = this.inputpassword.getText().toString().trim();
        final String phone = this.inputphonenumber.getText().toString().trim();
        final String bloodgroup = this.inputbloodgroup.getText().toString().trim();
        final String dob = this.inputdob.getText().toString().trim();

        String URL_REGST = "http://farwa.plenary-session.com/apis/usersignup.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGST, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {

                        Toast.makeText(UserSignUp.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),
                                UserLogIn.class);
                        startActivity(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(UserSignUp.this, "Registration Error" + e.toString(), Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.GONE);
                    btn_Register.setVisibility(View.VISIBLE);
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(UserSignUp.this, "Registration Error" + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_Register.setVisibility(View.VISIBLE);

                    }
                })

        {
            @Override
            protected Map<String, String> getParams(){

                Map<String, String> params = new HashMap<>();
                params.put("firstName",firstName);
                params.put("lastName",lastName);
                params.put("email", email);
                params.put("password", password);
                params.put("phone", phone);
                params.put("bloodgroup", bloodgroup);
                params.put("dob", dob);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
