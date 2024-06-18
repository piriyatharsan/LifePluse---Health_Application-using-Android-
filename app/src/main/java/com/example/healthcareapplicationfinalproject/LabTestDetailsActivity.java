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
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    EditText edTvPackageDetails;
    Button btnAddCart, btnBack;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tvPackageName = findViewById(R.id.textViewLDPackageName);
        tvTotalCost = findViewById(R.id.textViewLDTotalCost);
        edTvPackageDetails = findViewById(R.id.editTextLDMulitline);
        btnBack = findViewById(R.id.buttonLDLabBack);
        btnAddCart = findViewById(R.id.buttonLDGoToCart);
        database = new Database(this);
        edTvPackageDetails.setKeyListener(null);

        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edTvPackageDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total cost:"+intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
            }
        });

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("share_Prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                if (username == null || username.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username not found", Toast.LENGTH_SHORT).show();
                    Log.d("DEBUG", "Username not found in SharedPreferences");
                    return; // Exit the method if username is not found
                }
                else {
                    username = username.trim(); // Trim any leading or trailing spaces
                    String product = tvPackageName.getText().toString().trim();
                    Float price = Float.parseFloat(intent.getStringExtra("text3").toString());
                    // Log the retrieved values for debugging
                    Log.d("DEBUG", "Username: " + username);
                    Log.d("DEBUG", "Product: " + product);
                    Log.d("DEBUG", "Price: " + price);

                    if (database.checkCart(username,product)==1){
                        Toast.makeText(getApplicationContext(),"product Already Added",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        boolean insert = database.addCart(username, product, price, "Lab");
                        if (insert==true) {
                            Toast.makeText(getApplicationContext(), "Record Inserted into cart", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
                        }else
                        {
                            Toast.makeText(getApplicationContext(), "Record is not inserted into cart", Toast.LENGTH_SHORT).show();
                        }
                    }
                    }
            }
        });

    }
}