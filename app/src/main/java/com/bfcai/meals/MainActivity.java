package com.bfcai.meals;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // هنا عرفت المتغير بتاع الفايربيز و الصوره
    FirebaseAuth auth;
    ImageView logout;
    RecyclerView recyclerView;
    List<MealClass> meal;
//    private static String JSON_URL = "https://fathy.ids-soft.host/data.json";
    private static String JSON_URL = "https://fathy.ids-soft.host/";
    MealAdapter adapter;
    Toolbar toolbar;
    ProgressBar progressBar;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // عرفت زرار لي بينقل لصفحة الوصفات
        fab = findViewById(R.id.wasfaPage);

        // ربط الصوره ب ملف xml و عرفت الفايربيز
        logout = findViewById(R.id.logout);
        auth = FirebaseAuth.getInstance();

        // عرفت لما يدوس علي زرار الخروج يسجل خروج من الفايربيز ويرجع ل شاشة اللوجين
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getBaseContext(), loginActivity.class));
                finish();
            }
        });

        // عرفته انه لما ادوس علب الزرار يتنقل لصفحة الوصفات
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),WasafatyActivity.class));
            }
        });

        progressBar = findViewById(R.id.progress);

        recyclerView = findViewById(R.id.mealList);
        meal = new ArrayList<>();

        extractMeals();


    }

    private void extractMeals() {
        // بقوله العناصر وكدا هتتعرض في ريكويست جديده في انهي صفحه
        RequestQueue queue = Volley.newRequestQueue(this);

        // قولتله هستخدم الميثود get عشان هعرض البيانات وعرفته انها هتتعرض في array
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject songObject = response.getJSONObject(i);
                        MealClass meals = new MealClass();
                        meals.setTitle(songObject.getString("strMeal").toString());
                        meals.setprice(songObject.getString("price".toString()));
                        meals.setCoverImage(songObject.getString("strMealThumb"));
                        meal.add(meals);
                        progressBar.setVisibility(View.INVISIBLE);

                    } catch (JSONException e) {
                        Toast toast=Toast.makeText(getApplicationContext(),"JSON EXCEPTION e ERROR",Toast.LENGTH_SHORT);
                        toast.show();
                        e.printStackTrace();
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new MealAdapter(getApplicationContext(),meal,getBaseContext() , MainActivity.this);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast=Toast.makeText(getApplicationContext(),"Fetch data failed",Toast.LENGTH_LONG);
                toast.show();
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);
    }

}