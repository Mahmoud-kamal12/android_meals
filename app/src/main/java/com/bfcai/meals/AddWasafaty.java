package com.bfcai.meals;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddWasafaty extends AppCompatActivity {

    // عرفنا الحاجه لي هنحتاجها من صفحه الديزاين
    EditText title_input, price_input , name_input , phone_input , address_input;
    Button add_button;
    String title, price;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wasafaty);

        // ربطنا الحاجه بصفحة الديزاين
        title_input = findViewById(R.id.title_input);
        price_input = findViewById(R.id.price_input);
        name_input = findViewById(R.id.name_input);
        phone_input = findViewById(R.id.phone_input);
        address_input = findViewById(R.id.address_input);


        add_button = findViewById(R.id.add_button);

        getAndSetIntentData();


        // قولناله لما يدوس علي الزرار
        // يعمل انشاء وصفة جديده في الداتابيز
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddWasafaty.this);
                String dbTitle = title_input.getText().toString().trim();
                String dbPrice =price_input.getText().toString().trim();
                String dbName = name_input.getText().toString().trim();
                String dbPhone = phone_input.getText().toString().trim();
                String dbAddress = address_input.getText().toString().trim();

                myDB.addWasfa(dbTitle,dbPrice,dbName,dbPhone,dbAddress);

                startActivity(new Intent(getBaseContext(),WasafatyActivity.class));
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("title") && getIntent().hasExtra("price")){

            title = getIntent().getStringExtra("title");
            price = getIntent().getStringExtra("price");

            title_input.setText(title);
            price_input.setText(price.toString());

            Log.d("Mahmoud : " , "title = " + title + " price = " + price);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

}