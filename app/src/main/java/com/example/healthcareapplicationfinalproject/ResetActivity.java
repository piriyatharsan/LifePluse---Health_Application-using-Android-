package com.example.healthcareapplicationfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetActivity extends AppCompatActivity {


    TextView username;
    EditText pass, rePass;
    Button btnConfirm, btnBack;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        username = findViewById(R.id.usernameReset);
        pass = findViewById(R.id.passwordReset);
        rePass = findViewById(R.id.ReConfirmResetPassword);
        btnConfirm = findViewById(R.id.ResetButton);
        btnBack = findViewById(R.id.ResetBack);
        database = new Database(this);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));
        
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String password = pass.getText().toString();
                String conPass = rePass.getText().toString();

                if (password.equals("") || conPass.equals("")) {
                    Toast.makeText(ResetActivity.this, "Details not updated", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(conPass)) {

                        Boolean checkPasswordUpdate = database.updatePassword(user, password);
                        if (checkPasswordUpdate == true) {
                            Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent1);
                            Toast.makeText(ResetActivity.this, "Password is reset", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ResetActivity.this, "Password is not updated", Toast.LENGTH_SHORT).show();

                        }
                    }else {
                        Toast.makeText(ResetActivity.this, "Password is not Match", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });
    }
}