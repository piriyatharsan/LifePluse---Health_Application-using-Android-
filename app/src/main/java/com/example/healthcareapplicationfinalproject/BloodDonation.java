package com.example.healthcareapplicationfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class BloodDonation extends AppCompatActivity {

    EditText edusername,edbloodtype,edgender,ednearhospital,ednumber;
    Button submit;
    Database database;
    private Spinner spinnerBloodType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donation2);

        edusername=findViewById(R.id.editTextUsername);
//        edbloodtype=findViewById(R.id.editTextBloodType);
        edgender=findViewById(R.id.editTextGender);
        ednearhospital=findViewById(R.id.editTextNearHospital);
        ednumber=findViewById(R.id.editTextNumber);
        submit=findViewById(R.id.ButtonForRequest);
        spinnerBloodType = findViewById(R.id.spinnerBlood);

        database = new Database(this);


        // Populate the Spinner with categories
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.blood_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBloodType.setAdapter(adapter);

        // Handle Spinner item selection
        spinnerBloodType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle no selection
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edusername.getText().toString();
//                String bloodtype = edbloodtype.getText().toString();
                String gender = edgender.getText().toString();
                String nearhospital = ednearhospital.getText().toString();
                String number = ednumber.getText().toString();
                String bloodtype = spinnerBloodType.getSelectedItem().toString();

                if (username.equals("")|| bloodtype.equals("")|| gender.equals("")|| nearhospital.equals("")|| number.equals("")){
                    Toast.makeText(getApplicationContext(), "Please fill the details", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean insert = database.bloodRequest(username,bloodtype,gender,nearhospital,number);
                    if (insert==true){
                        Toast.makeText(BloodDonation.this,"Blood request has been sent",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), "Blood request is not sent", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });



    }
}