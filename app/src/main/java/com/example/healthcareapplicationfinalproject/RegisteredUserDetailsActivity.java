package com.example.healthcareapplicationfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisteredUserDetailsActivity extends AppCompatActivity {

    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_user_details);

        // Initialize UI components
        btnBack = findViewById(R.id.btnBackUserDetails);
        lst = findViewById(R.id.listViewUserDetails);
        database = new Database(this);

        // Set up back button click listener
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisteredUserDetailsActivity.this, DashboardActivity.class));
            }
        });

        // Fetch all users data from the database
        ArrayList<String> dbData = database.getAllUsersData();

        // Initialize list to hold data for adapter
        list = new ArrayList<>();

        // Parse and prepare data
        for (String arrData : dbData) {
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            item = new HashMap<>();
            item.put("line1", strData.length > 0 ? strData[0] : "");
            item.put("line2", strData.length > 1 ? strData[1] : "");
            item.put("line3", strData.length > 2 ? strData[2] : "");
            list.add(item);
        }

        // Set up SimpleAdapter
        sa = new SimpleAdapter(this, list,
                R.layout.users, new String[]{"line1", "line2", "line3"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c});
        lst.setAdapter(sa);
    }
}
