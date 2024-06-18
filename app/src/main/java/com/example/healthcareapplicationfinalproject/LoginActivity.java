package com.example.healthcareapplicationfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    Button logBtn;
    TextView tv , resetPassword;
    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUserName = findViewById(R.id.editTextRegUsername);
        edPassword = findViewById(R.id.editTextRegConPassword);
        logBtn = findViewById(R.id.ButtonForRegister);
        tv = findViewById(R.id.textforLogin);
        resetPassword = findViewById(R.id.resetPassword);

        database =new Database(this);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUserName.getText().toString();
                String password = edPassword.getText().toString();

                if(username.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(),"Please fill the Details",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkUserPass =database.checkUserNamePassword(username,password);
                    if (checkUserPass==true){
                        Toast.makeText(LoginActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("share_Prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",username);
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this,"Invalid username and password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}