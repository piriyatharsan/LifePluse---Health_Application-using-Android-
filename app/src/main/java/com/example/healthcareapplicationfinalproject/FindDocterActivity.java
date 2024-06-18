package com.example.healthcareapplicationfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDocterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_docter);

        CardView familyDocter = findViewById(R.id.fdCardfamilyDocter);
        familyDocter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(FindDocterActivity.this,DocterDetailsActivity.class);
                i1.putExtra("title","Family Docters");
                startActivity(i1);
            }
        });
        CardView dentist = findViewById(R.id.fdCardDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(FindDocterActivity.this,DocterDetailsActivity.class);
                i1.putExtra("title","Dentist");
                startActivity(i1);
            }
        });
        CardView surgeon = findViewById(R.id.fdCardSurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(FindDocterActivity.this,DocterDetailsActivity.class);
                i1.putExtra("title","Surgeon");
                startActivity(i1);
            }
        });

        CardView dieticion = findViewById(R.id.fdCardDieticion);
        dieticion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(FindDocterActivity.this,DocterDetailsActivity.class);
                i1.putExtra("title","Dietician");
                startActivity(i1);
            }
        });

        CardView cardiologist = findViewById(R.id.fdCardCardiologists);
        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(FindDocterActivity.this,DocterDetailsActivity.class);
                i1.putExtra("title","Cardiologist");
                startActivity(i1);
            }
        });

        CardView neurologist = findViewById(R.id.fdCardNeurologist);
        neurologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(FindDocterActivity.this,DocterDetailsActivity.class);
                i1.putExtra("title","Neurologist");
                startActivity(i1);
            }
        });
    }
}