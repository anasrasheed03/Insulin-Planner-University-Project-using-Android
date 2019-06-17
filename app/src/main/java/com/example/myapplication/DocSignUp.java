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

public class DocSignUp extends AppCompatActivity {

    private EditText inputfirstname;
    private EditText inputlastname;
    private EditText inputhospital;
    private EditText inputphonenumber;
    private EditText inputemail;
    private EditText inputpassword;
    private EditText inputspecialization;
    private Button btn_Register,btnLinkToLogin;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_sign_up);
        inputfirstname = findViewById(R.id.f_name);
        inputlastname = findViewById(R.id.l_name);
        inputhospital = findViewById(R.id.hospital);
        inputphonenumber = findViewById(R.id.phone);
        inputemail = findViewById(R.id.email);
        inputpassword = findViewById(R.id.password);
        loading = findViewById(R.id.loading);
        btn_Register = findViewById(R.id.btn_register);
        inputspecialization = findViewById(R.id.specialization);

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });




    }
    private void Register(){
        loading.setVisibility(View.VISIBLE);
        btn_Register.setVisibility(View.GONE);



        final String firstName = this.inputfirstname.getText().toString().trim();
        final String lastName = this.inputlastname.getText().toString().trim();
        final String email = this.inputemail.getText().toString().trim();
        final String password = this.inputpassword.getText().toString().trim();
        final String phone = this.inputphonenumber.getText().toString().trim();
        final String hospital = this.inputhospital.getText().toString().trim();
        final String specialization = this.inputspecialization.getText().toString().trim();
        String URL_REGST = "http://farwa.logicalhive.com/apis/docsignup.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGST, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {

                        Toast.makeText(DocSignUp.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),
                                UserLogIn.class);
                        startActivity(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(DocSignUp.this, "Registration Error" + e.toString(), Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.GONE);
                    btn_Register.setVisibility(View.VISIBLE);
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(DocSignUp.this, "Registration Error" + error.toString(), Toast.LENGTH_SHORT).show();
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
                params.put("hospital", hospital);
                params.put("specialization", specialization);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
