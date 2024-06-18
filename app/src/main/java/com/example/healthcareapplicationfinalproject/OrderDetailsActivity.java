package com.example.healthcareapplicationfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderDetailsActivity extends AppCompatActivity {
    CardView registeredUser, DoctorAppoinment,BloodRequest,LabRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);


        registeredUser = findViewById(R.id.RegisterUser);
        registeredUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, RegisteredUserDetailsActivity.class));
            }
        });
        DoctorAppoinment = findViewById(R.id.DocterAppoinment);
        DoctorAppoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, AppoinmentDetailsActivity.class));
            }
        });
        BloodRequest = findViewById(R.id.BloodRequest);
        BloodRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, RequestBloodActivity.class));
            }
        });
        LabRequest = findViewById(R.id.LabRequest);
        LabRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, LabOrderDetailsActivity.class));
            }
        });

        CardView LogOut = findViewById(R.id.Back);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, DashboardActivity.class));
            }
        });
        CardView DoctorInfo = findViewById(R.id.DoctorUpdate);
        DoctorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, DoctorProfileActivity.class));
            }
        });
//        Button Back = findViewById(R.id.OrderBack);




//        Back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(OrderDetailsActivity.this, DashboardActivity.class));
//            }
//        });
    }

}