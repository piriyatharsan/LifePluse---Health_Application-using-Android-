package com.example.healthcareapplicationfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername, edEmail,edPassword, edConPassword;
    Button regBtn;
    TextView tv;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUsername = findViewById(R.id.editTextRegUsername);
        edEmail = findViewById(R.id.editTextRegEmail);
        edPassword = findViewById(R.id.editTextRegPassword);
        edConPassword = findViewById(R.id.editTextRegConPassword);
        regBtn = findViewById(R.id.ButtonForRegister);
        tv = findViewById(R.id.textforLogin);
        database =new Database(this);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String conPassword = edConPassword.getText().toString();

                if (username.equals("") || email.equals("")|| password.equals("") || conPassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please fill the details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (password.compareTo(conPassword)==0)
                    {
                        String em = edEmail.getText().toString().trim();
                        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                        if (em.matches(emailPattern)) {
                            if (isValid(password)) {
                                Boolean checkUser = database.checkUserName(username);
                                if (checkUser==false)
                                {
                                    Boolean insert = database.register(username,email ,password);
                                    if (insert==true)
                                    {
                                        Toast.makeText(RegisterActivity.this,"Record updated",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast.makeText(RegisterActivity.this,"Record not updated",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(RegisterActivity.this,"User Already Exit",Toast.LENGTH_SHORT).show();
                                }
                    }
                            else {
                                Toast.makeText(RegisterActivity.this,"Password Not match",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegisterActivity.this,"Email is not vaild",Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
    }
    public static boolean isValid(String password){
        int t1=0,t2=0,t3=0,t4=0;
        if (password.length() < 8){
            return false;
        }
        else {
            for (int p=0;p<password.length();p++){
                if (Character.isLetter(password.charAt(p))){
                    t1=1;
                }
            }
            for (int r=0;r<password.length();r++){
                if (Character.isDigit(password.charAt(r))){
                    t2=1;
                }
            }
            for (int s=0;s<password.length();s++){
                char c = password.charAt(s);
                if (c>=33 && c<=46 || c == 64){
                    t3=1;
                }
            }
            if (t1==1 && t2==1 && t3 == 1){
                return true;
            }
            return false;
        }
    }
}

