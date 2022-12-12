package com.bfcai.meals;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateWasafaty extends AppCompatActivity {

    EditText title_input, price_input , name_input , phone_input , address_input;
    Button update_button, delete_button;

    String id, title, price , name , address , phone;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_wasafaty);

        title_input = findViewById(R.id.title_input);
        price_input = findViewById(R.id.price_input);
        name_input = findViewById(R.id.name_input);
        phone_input = findViewById(R.id.phone_input);
        address_input = findViewById(R.id.address_input);

        delete_button = findViewById(R.id.delete_button);
        update_button = findViewById(R.id.update_button);


        //First we call this
        getAndSetIntentData();


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(updateWasafaty.this);
                String dbTitle = title_input.getText().toString().trim();
                String dbPrice =price_input.getText().toString().trim();
                String dbName = name_input.getText().toString().trim();
                String dbPhone = phone_input.getText().toString().trim();
                String dbAddress = address_input.getText().toString().trim();
                String row_id = address_input.getText().toString().trim();

                myDB.updateData(id ,dbTitle,dbPrice,dbName,dbPhone,dbAddress);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            price = getIntent().getStringExtra("price");
            name = getIntent().getStringExtra("name");
            phone = getIntent().getStringExtra("phone");
            address = getIntent().getStringExtra("address");

            //Setting Intent Data
            title_input.setText(title);
            price_input.setText(price);
            name_input.setText(name);
            phone_input.setText(phone);
            address_input.setText(address);

            Log.d("Mahmoud : ", id +" , "  + title + " , " + price);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(updateWasafaty.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}