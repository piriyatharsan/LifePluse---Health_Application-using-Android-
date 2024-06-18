package com.example.healthcareapplicationfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LabTestBookActivity extends AppCompatActivity {

    EditText edName,edAddress,edContact,edPinCode;
    Button btnBooking,back;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edName = findViewById(R.id.editTextTestBookFullName);
        edAddress = findViewById(R.id.editTextTestBookAddress);
        edContact = findViewById(R.id.editTextTestBookNumber);
        edPinCode = findViewById(R.id.editTextTestBookPinCode);
        btnBooking= findViewById(R.id.ButtonBookLabTest);
        back = findViewById(R.id.buttonBackLabTest);
        database = new Database(this);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestBookActivity.this, CartLabActivity.class));
            }
        });

        Intent intent = getIntent();
        String priceStr = intent.getStringExtra("price");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        String[] price = priceStr.split(java.util.regex.Pattern.quote(":"));


        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("share_Prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                database.addOrder(username,edName.getText().toString(),edAddress.getText().toString(),edContact.getText().toString(),Integer.parseInt(edPinCode.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"Lab");
                database.removeCart(username,"Lab");
                Toast.makeText(getApplicationContext(),"Your Booking is done Successfully",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LabTestBookActivity.this, DashboardActivity.class));
            }
        });
    }




}
