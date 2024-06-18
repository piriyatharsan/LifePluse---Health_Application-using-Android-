package com.example.healthcareapplicationfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {
    EditText username;
    Button reset;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        username = findViewById(R.id.usernameReset);
        reset = findViewById(R.id.reset);

        database = new Database(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                Boolean checkUser = database.checkUserName(user);
                if (checkUser==true){
                    Intent intent = new Intent(getApplicationContext(),ResetActivity.class);
                    intent.putExtra("username",user);
                    startActivity(intent);
                }else {
                    Toast.makeText(PasswordActivity.this,"User Does not exit",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}