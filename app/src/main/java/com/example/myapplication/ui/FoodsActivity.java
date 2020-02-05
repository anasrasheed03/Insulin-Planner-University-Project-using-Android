package com.example.myapplication.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.adapters.FoodsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoodsActivity extends AppCompatActivity {

    private static final String TAG = FoodsActivity.class.getSimpleName(); //get the information
    //private TextView maslaq;
    private String Textv;
    public static final String foodName = "foodName";
    public static final String foodQauntity = "foodQauntity";
    public static final String foodCalorie = "foodCalorie";

    //a list to store all the Product
    private List<Food> foodList;

    //the recyclerview
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_diet_chart);
        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        foodList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();
    }

    private final String URL_PRODUCTS = "http://farwa.logicalhive.com/apis/foodlist.php";

    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject Fatwa = array.getJSONObject(i);



                                //adding the product to product list
                                foodList.add(new Food(
                                        Fatwa.getString("foodName"),
                                        Fatwa.getString("foodQuantity"),
                                        Fatwa.getString("foodCalorie")
                                ));
                            }
Log.d("Food Data:", foodList.toString());
                            //creating adapter object and setting it to recyclerview
                            FoodsAdapter adapter = new FoodsAdapter(FoodsActivity.this, foodList);


                            recyclerView.setAdapter(adapter);

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


        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }


}

