package com.example.myapplication.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.adapters.EbookAdapter;
import com.example.myapplication.model.EbookData;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Ebook extends AppCompatActivity {



    final String URL_GET_DATA = "http://farwa.plenary-session.com/apis/ebooklist.php";
    RecyclerView recyclerView;
//    private static  final String TAG = Ebook.class.getSimpleName();
    EbookAdapter adapter;
    List<EbookData> heroList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);

        recyclerView = (RecyclerView) findViewById(R.id.ebookList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        heroList = new ArrayList<>();

        loadEbooks();

    }
    private void loadEbooks() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            Log.d("Response: ", jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
//                            Log.d("Json Response: ", obj.toString());

                                heroList.add(new EbookData(

                                        obj.getString("id"),
                                        obj.getString("bookname"),
                                        obj.getString("title"),
                                        obj.getString("shortDescription"),
                                        obj.getString("booktopic"),
                                        obj.getString("booktext"),
                                        obj.getString("docEmail")
                                ));
//Log.d("Hero: ", hero.toString());
//                                heroList.add(hero);
//                                Log.d("HeroList: ", heroList.toString());
                            }

                            adapter = new EbookAdapter(heroList, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
