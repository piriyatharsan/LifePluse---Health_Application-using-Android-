package com.example.healthcareapplicationfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthArticleDetailsActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article_details);

        textView = findViewById(R.id.textViewHATitle);
        imageView = findViewById(R.id.imageViewHAD);
        btnBack = findViewById(R.id.ButtonBackHAD);

        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("text1"));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            int resId = bundle.getInt("text2");
            imageView.setImageResource(resId);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticleDetailsActivity.this,HealthArticleActivity.class));

            }
        });
    }
}